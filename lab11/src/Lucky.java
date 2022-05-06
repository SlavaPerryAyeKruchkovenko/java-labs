import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {
    static int x = 0;
    static int count = 0;
    private static final Object lock = new Object();
    private static final Object lock2 = new Object();

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            int xCopy = 0;
            while (true) {
                synchronized (lock){
                        if(x < 999999) {
                            x++;
                            xCopy = x;
                        }
                        else{
                            break;
                        }
                }
                if ((xCopy % 10) + (xCopy / 10) % 10 + (xCopy / 100) % 10 == (xCopy / 1000)
                        % 10 + (xCopy / 10000) % 10 + (xCopy / 100000) % 10) {
                    //System.out.println(x);
                    System.out.println(xCopy + " " + Thread.currentThread().getName());
                    synchronized (lock2){
                        count++;
                    }
                }
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        //System.out.println((time - System.currentTimeMillis())/1000);
        System.out.println("Total: " + count);
    }
}