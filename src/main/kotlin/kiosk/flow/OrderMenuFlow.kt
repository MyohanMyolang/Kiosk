package kiosk.flow

import common.util.InputUtil
import kiosk.menu.Menu
import kiosk.menu.enum.MenuCategory
import kiosk.order.Order
import kiosk.user.User


class OrderMenuFlow(private val menuList: HashMap<MenuCategory, ArrayList<Menu>>) {

    private fun getWhatTodo(): Int? {
        var menuIdx:Int;
        while (true) {
            var idx = 1;
            println("0. 주문하기")
            menuList.forEach { entry ->
                println("${idx++}. ${entry.key}  | ${entry.key.getIntro()}");
            }
            menuIdx = InputUtil.getInputNumber("선택하여 주십시오.", fun(num:Int): Boolean{
                return when(num){
                    in (0..menuList.size) -> true
                    else -> {
                        println("0-${menuList.size}내의 값을 입력하여 주십시오.")
                        false
                    };
                }
            });
            if(menuIdx == -1) return null;

            break;
        }



        return menuIdx;
    }

    fun getMenu(key: Int): Menu?{
        val list = menuList[MenuCategory.valueOf(key)] ?: throw Exception("Key가 등록되어 있지 않습니다. 다시 한 번 확인하여 주십시오.");
        println("[ Sparta ${MenuCategory.valueOf(key)!!.getKey()} ]")
        if(list.size == 0){
            println("메뉴가 존재하지 않습니다.");
            return null;
        }

        list.forEachIndexed{index, menu ->
            println("${index+1}. $menu")
        }

        val index = InputUtil.getInputNumber("선택하여 주십시오.", fun(num):Boolean {
            return when(num){
                in (1..list.size) -> true
                else -> {
                    println("1-${list.size}의 숫자를 입력하여 주십시오.")
                    false
                }
            }
        })
        if (index==-1) return null;


        return list[index-1];
    }

    fun start(user: User){
        while(true) {
            val key = getWhatTodo() ?: return;
            if (key == 0) {
                val result = Order().reqOrder(user);
                when(result){
                    1 -> {
                        return
                    }
                    0 -> {
                        continue
                    }
                    -1 -> {
                        break;
                    }
                    -2 ->{
                        println("주문 불가!");
                        break;
                    }
                }
            }
            val menu = getMenu(key) ?: return;
            user.addMenu(menu);
        }
    }
}