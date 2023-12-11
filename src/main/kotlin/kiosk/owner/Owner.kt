package kiosk.owner

class Owner {
    private var amount: Int = 0;

    fun recivePay(money: Int){
        amount+=money;
    }
}