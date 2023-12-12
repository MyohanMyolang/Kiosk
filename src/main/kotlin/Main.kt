import kiosk.Kiosk
import kiosk.notify.Notify

suspend fun main(args: Array<String>) {
    Kiosk().startService();
    Notify.stopAsyncScope();
}