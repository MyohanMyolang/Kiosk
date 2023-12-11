package kiosk.flow

import kiosk.menu.Menu
import kiosk.menu.enum.MenuCategory


class AddMenuFlow(private val menuList: HashMap<MenuCategory, ArrayList<Menu>>): Flow() {

    override fun start() {
        println("start Add Menu Flow")
        menuList.get(MenuCategory.IceCream);
    }
}