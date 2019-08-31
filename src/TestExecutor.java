import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestExecutor {
    public static void main(String[] args) throws InterruptedException {

        var main = new TestExecutor();
        main.test();

    }

    public void test() throws InterruptedException {
        var executor = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        executor.allowCoreThreadTimeOut(true);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("Anh quy luon dung =))");
            }
        });
        executor.submit(t1);


        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Anh quy luon dung =))");
        });

        Thread.sleep(10000);

        System.out.println(executor.isShutdown());
        System.out.println(executor.isTerminated());
        System.out.println(executor.isTerminating());
        System.out.println(executor.getActiveCount());
        System.out.println(executor.getCorePoolSize());
        System.out.println(executor.allowsCoreThreadTimeOut());
        System.out.println(t1.isAlive());

        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Anh tu luon dung =))");
        });
        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Anh tu luon dung =))");
        });

        System.out.println(executor.isShutdown());
        System.out.println(executor.isTerminated());
        System.out.println(executor.isTerminating());
        System.out.println(executor.getActiveCount());
        System.out.println(executor.getCorePoolSize());

    }
}
