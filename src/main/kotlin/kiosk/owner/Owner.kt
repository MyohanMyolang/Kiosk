package kiosk.owner

object Owner {
    private var amount: Int = 0;
    private val pw:String = "password";

    fun checkPw(pw: String): Boolean{
        return this.pw == pw;
    }

    fun receivePay(money: Int){
        amount+=money;
    }
}