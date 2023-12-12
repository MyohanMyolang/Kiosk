package kiosk.notify

import kiosk.menu.Menu
import kotlinx.coroutines.*
import java.util.*

object Notify {
    private var waitNum = 0;
    private val asyncScope = CoroutineScope(Dispatchers.Default);
    private var orderNum: Int = 0;

    private suspend fun printWaitNum(delay: Long){
        while(true){
            println("대기 숫자 : $waitNum");
            delay(delay)
        }
    }

    fun startPrintWaitNum(delay : Int){
        asyncScope.launch{
            printWaitNum(5*1000)
        }
    }

    fun stopAsyncScope(){
        asyncScope.cancel();
    }

    private suspend fun cookMenu(time:Long, num:Int){
        delay(time*1000)
        println("주문번호 ${num} 완료");
        waitNum--;
    }


    /**
     * @return 주문 번호
     */
    fun addOrder(menuList: LinkedList<Menu>):Int {
        var totalCookTime = 0;
        menuList.forEach{
            totalCookTime += it.cookTime;
        }
        waitNum++;
        orderNum++;
        asyncScope.launch {
            cookMenu(totalCookTime.toLong(), orderNum);
        }

        return orderNum;
    }
}