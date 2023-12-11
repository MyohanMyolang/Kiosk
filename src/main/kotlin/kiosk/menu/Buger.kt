package kiosk.menu

class Buger(name: String, cookTime: Int, price: Int, private val intro: String) : Menu(name, cookTime, price) {

    override fun toString(): String = "W ${decimalFormat.format(price/1000)} | ${intro}"

}