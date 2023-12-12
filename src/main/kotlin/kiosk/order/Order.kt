package kiosk.order

import common.util.InputUtil
import kiosk.menu.Menu
import kiosk.notify.Notify
import kiosk.owner.Owner
import kiosk.user.User
import java.time.LocalTime

class Order {


    fun modifyMenuList(list: ArrayList<Menu>){

    }

    fun checkCanOrderTime():Boolean{
        val curTime = LocalTime.now();
        val startTime = LocalTime.parse("18:00");
        val endTime = LocalTime.parse("19:00");

        return !(curTime.isAfter(startTime) && curTime.isBefore(endTime))
    }

    /**
     * @return 성공 1, 메뉴추가 0, 초기화면 -1, 실패 -2
     */
    fun reqOrder(user: User): Int{
        while(true) {
            var totalPrice: Int = 0;
            println("장바구니 목록");
            user.basket.forEachIndexed { index, menu ->
                println("${index + 1}. ${menu}")
                totalPrice += menu.price;
            }
            println("총 금액 - ${totalPrice}");
            println("어떤 동작을 하시겠습니까?")
            val idx = InputUtil.getInputNumber("0. 메뉴 추가 \n1. 주문 \n2. 목록 수정", fun(num): Boolean {
                return when (num) {
                    in (0..2) -> true
                    else -> {
                        println("1-2 사이의 숫자를 입력하여 주십시오.");
                        false
                    }
                }
            })
            if (idx <= 0) {
                println("주문 취소");
                return idx;
            }
            when (idx) {
                1 -> {
                    if(!user.pay(totalPrice, Owner)){
                        return -2;
                    }
                    if(!checkCanOrderTime()){
                        println("주문 가능한 시간이 아닙니다.")
                        return -2;
                    }
                    val orderNum = Notify.addOrder(user.basket);
                    println("주문완료 - 주문번호 ${orderNum}");
                    break
                }

                2 -> {
                    TODO("수정");
                    continue
                }
            }
        }
        return 1;
    }
}