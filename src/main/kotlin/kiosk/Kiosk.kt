package kiosk

import kiosk.menu.Menu
import kiosk.menu.enum.MenuCategory
import kiosk.owner.Owner

class Kiosk {
    val owser: Owner = Owner();
    val menuList: HashMap<MenuCategory, ArrayList<Menu>> = HashMap<MenuCategory, ArrayList<Menu>>();
}