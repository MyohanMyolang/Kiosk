package kiosk.notify

import kotlinx.coroutines.*

object Notify {
    var waitNum = 0;
    private val asyncScope = CoroutineScope(Dispatchers.Default);

    private suspend fun printWaitNum(delay: Long){
        while(true){
            println("\n대기 숫자 : $waitNum\n");
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
}