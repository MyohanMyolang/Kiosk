package kiosk.menu

import kiosk.menu.enum.MenuCategory

class IceCream(name: String, cookTime: Int, price: Int, private val intro: String) : Menu(name, cookTime, price)  {
    companion object{
        val category: MenuCategory = MenuCategory.IceCream;
    }

    fun test(){
        MenuCategory.valueOf(1);
    }

    override fun toString(): String = "W ${decimalFormat.format(price/1000)} | ${intro}"
}