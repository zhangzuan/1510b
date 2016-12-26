package activity.bawe.com.erweimasaomiao;

/**
 * 1、类型：
 * 2、作者：张钻
 * 3、时间：2016-11-23
 */

public class Thread1 extends Thread {
    public static int ticket = 100;
    public static Object object = new Object();

    public Thread1() {
    }

    @Override
    public void run() {
        super.run();

        while (true) {
            // t1 t2
            // t1进入锁的代码块 不会
            // cpu执行权
            // t1 抢到cpu的执行权 sleep不会释放执行权
            synchronized (object) {
                if (ticket > 0) {
                    System.out.println(this.getName() + "-----当前卖出的票是"
                            + --ticket);
                }
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
