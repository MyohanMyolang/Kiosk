package kiosk

import kiosk.menu.Menu
import kiosk.menu.enum.MenuCategory
import kiosk.owner.Owner
import common.util.InputUtil
import kiosk.flow.OrderMenuFlow
import kiosk.notify.Notify
import kiosk.user.User
import kotlinx.coroutines.delay

class Kiosk {
    val owner: Owner = Owner();
    val menuList: HashMap<MenuCategory, ArrayList<Menu>> = LinkedHashMap<MenuCategory, ArrayList<Menu>>();

    init {
        MenuCategory.entries.forEach{
            menuList[it] = ArrayList<Menu>();
        }

        Notify.startPrintWaitNum(5);
    }

    private fun showServiceMenu(){
        println("[ Kiosk Menu ]")
        println("1. 메뉴 담기")
    }

    private fun startFlow(): Boolean {
        var result: Int = InputUtil.getInputNumber("선택하여 주십시오.")
        return checkOrder(result);
    }

    private fun checkOrder(order: Int): Boolean {
        return when(order){
            1 -> {
                val user:User = User((50000..500000).random());
                OrderMenuFlow(menuList).start(user);

                false
            }
            -1 -> {
                println("값이 입력되지 않았습니다.")
                false
            };
            else -> {
                println("1-6 사이의 값을 입력하여 주십시오.")
                false
            };
        }
    }

    suspend fun startService(){
        while(true){
            val user:User = User((50000..300000).random());
            println("소지 금액 : ${user.getAmount()}")
            showServiceMenu();
            if(!startFlow()){
                delay(3000);
                continue
            }

        }
    }
}