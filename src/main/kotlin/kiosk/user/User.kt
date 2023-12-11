package kiosk.user

import kiosk.menu.Menu
import kiosk.owner.Owner
import java.util.LinkedList

class User(private var amount:Int) {
    val basket: LinkedList<Menu> = LinkedList<Menu>();

    fun getAmount(): Int = amount;

    fun pay(pay: Int, target: Owner): Boolean{
        if(amount <= pay){
            println("금액이 부족 합니다.");
            return false;
        }
        amount -= pay;
        target.receivePay(pay);
        return true;
    }

    fun addMenu(item: Menu){
        basket.add(item);
    }

    fun getOrder(): LinkedList<Menu>{
        val result: LinkedList<Menu> = basket.clone() as LinkedList<Menu>

        basket.clear();

        return result;
    }
}