package kiosk.menu.enum

enum class MenuCategory(private val key: String, private val intro: String, private val num: Int) {
    Buger("Burger", "버거는 버거라서 버거다.", 1),
    IceCream("IceCream", "신선한 신개념 따뜻한 아이스크림", 2);

    companion object{
        fun valueOf(num: Int): MenuCategory? {
            return when(num){
                1 -> Buger;
                2 -> IceCream;
                else -> null;
            }
        }
    }

    fun getKey(): String = key
    fun getIntro(): String = intro
}