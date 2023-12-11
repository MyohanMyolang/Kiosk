package kiosk

import kiosk.menu.Menu
import kiosk.menu.enum.MenuCategory
import kiosk.owner.Owner
import common.util.InputUtil
import kiosk.flow.AddMenuFlow
import kiosk.flow.Flow
import kiosk.user.User

class Kiosk {
    val owser: Owner = Owner();
    val menuList: HashMap<MenuCategory, ArrayList<Menu>> = HashMap<MenuCategory, ArrayList<Menu>>();
    private val flowArr:Array<Flow> = arrayOf<Flow>(
        AddMenuFlow(menuList),

    )

    private fun showServiceMenu(){
        println("[ Kiosk Menu ]")
        println("1. 메뉴 담기")
    }

    private fun getFlow(): Flow? {
        var result: Int = InputUtil.getInputNumber("선택하여 주십시오.")
        return checkOrder(result);
    }

    private fun checkOrder(order: Int): Flow? {
        return when(order){
            in (1..1) -> flowArr[order-1]
            -1 -> {
                println("값이 입력되지 않았습니다.")
                null
            };
            else -> {
                println("1-6 사이의 값을 입력하여 주십시오.")
                null
            };
        }
    }

    fun startService(){
        var flow:Flow;
        val user:User = User((50000..300000).random());
        while(true){
            println("소지 금액 : ${user.getAmount()}")
            showServiceMenu();
            flow = getFlow() ?: continue;
            flow.start();
        }
    }
}