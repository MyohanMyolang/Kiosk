package kiosk

import kiosk.menu.Menu
import kiosk.menu.enum.MenuCategory
import common.util.InputUtil
import kiosk.flow.OrderMenuFlow
import kiosk.menu.Buger
import kiosk.menu.IceCream
import kiosk.notify.Notify
import kiosk.user.User
import kotlinx.coroutines.*

class Kiosk {
    val menuList: HashMap<MenuCategory, ArrayList<Menu>> = LinkedHashMap<MenuCategory, ArrayList<Menu>>();

    init {
        MenuCategory.entries.forEach{
            menuList[it] = ArrayList<Menu>();
        }

        menuList[MenuCategory.Buger]?.add(Buger("불고기버거", 60, 5900, "태워서 드립니다."));
        menuList[MenuCategory.Buger]?.add(Buger("물고기버거", 3, 5200, "싱싱하게 살아있는 채로 드립니다."));

        menuList[MenuCategory.IceCream]?.add(IceCream("민트 초코", 20, 6200, "말이 필요 없는 맛"));
        menuList[MenuCategory.IceCream]?.add(IceCream("치약 초고", 10, 5200, "치약 듬뿍! 초코 듬뿍!"));

        Notify.startPrintWaitNum(5);
    }

    private fun showServiceMenu(){
        println("[ Kiosk Menu ]")
        println("1. 메뉴 담기")
        println("4. 종료");
    }

    private fun startFlow(): Boolean {
        var result: Int = InputUtil.getInputNumber("선택하여 주십시오.")
        return checkFlow(result);
    }

    private fun checkFlow(order: Int): Boolean {
        return when(order){
            1 -> {
                val user:User = User((50000..500000).random());
                println("소지 금액 : ${user.getAmount()}")
                OrderMenuFlow(menuList).start(user);

                true
            }
            4 -> {
                println("종료");
                false
            }
            -1 -> {
                println("값이 입력되지 않았습니다.")
                true
            };
            else -> {
                println("1-6 사이의 값을 입력하여 주십시오.")
                true
            };
        }
    }

    suspend fun startService(){
        while(true){
            showServiceMenu();
            if(!startFlow()) break;

            println("3초 대기")
            val wait = CoroutineScope(Dispatchers.Default).async {
                delay(3000);
            }
            wait.await();
        }
    }
}