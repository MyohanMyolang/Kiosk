package kiosk.menu

import java.text.DecimalFormat

abstract class Menu(val name: String, val cookTime: Int, val price: Int) {

    companion object{
        @JvmStatic
        protected val decimalFormat = DecimalFormat("##00.0")
    }

    /**
     * @return ex) price 5.0 | Intro
     */
    abstract override fun toString(): String;

}