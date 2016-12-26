package activity.bawe.com.erweimasaomiao;

/**
 * 1、类型：
 * 2、作者：张钻
 * 3、时间：2016-11-23
 */

public class Thread2 implements Runnable {

    private int ticket = 100;
    private Object object = new Object();

    @Override
    public void run() {
        while (true) {
            // t1 t2
            // t1进入锁的代码块 不会
            // cpu执行权
            // t1 抢到cpu的执行权 sleep不会释放执行权
            synchronized (object) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName()
                            + "-----当前卖出的票是" + --ticket);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
