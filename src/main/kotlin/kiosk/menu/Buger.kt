package kiosk.menu

class Buger(name: String, cookTime: Int, price: Int, private val intro: String) : Menu(name, cookTime, price) {

    override fun toString(): String = "${name} | W ${decimalFormat.format(price.toDouble()/1000)} | ${intro}"

}