package kiosk.flow

import common.util.InputUtil
import kiosk.menu.Menu
import kiosk.menu.enum.MenuCategory
import kiosk.user.User


class OrderMenuFlow(private val menuList: HashMap<MenuCategory, ArrayList<Menu>>) {

    private fun getFoodMenuKey(): MenuCategory? {
        var key: MenuCategory? = null;
        var menuIdx:Int;
        while (true) {
            var idx = 1;
            menuList.forEach { entry ->
                println("${idx++}. ${entry.key}  | ${entry.key.getIntro()}");
            }
            menuIdx = InputUtil.getInputNumber("선택하여 주십시오.", fun(num:Int): Boolean{
                return when(num){
                    in (1..menuList.size) -> true
                    else -> {
                        println("1-${menuList.size}내의 값을 입력하여 주십시오.")
                        false
                    };
                }
            });
            if(menuIdx == -1) return null;

            break;
        }

        key = MenuCategory.valueOf(menuIdx);

        if (key == null) throw Exception("Key가 등록되어 있지 않습니다. 다시 한 번 확인하여 주십시오.");


        return key;
    }

    fun getMenu(key: MenuCategory): Menu?{
        println("[ Sparta ${key.getKey()} ]")

        return null;
    }

    fun start(user: User){
        val key = getFoodMenuKey() ?: return;
        val Menu = getMenu(key) ?: return;
    }
}