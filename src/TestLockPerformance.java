import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLockPerformance {

    int count = 0;
    int n = 10000000;

    AtomicInteger count2 = new AtomicInteger(0);

    public static void main(String[] args) {
        var runner = new TestLockPerformance();
        runner.test1();
        runner.test1();
        runner.test1();
        runner.test2();
        runner.test2();
        runner.test2();

        System.out.println("Main: " + Thread.currentThread().getName());
    }

    public void test1() {
        var executor1 = Executors.newSingleThreadExecutor();
        executor1.submit(() -> {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                incInt();
            }
            System.out.println(Thread.currentThread().getName() + " - Time run test1 - lock: " + (System.currentTimeMillis() - startTime));
            System.out.println("Lock value: " + count);
        });
        executor1.shutdown();
    }

    public void test2() {
        var executor1 = Executors.newSingleThreadExecutor();
        executor1.submit(() -> {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                incInt2();
            }
            System.out.println(Thread.currentThread().getName() + " - Time run test2 - atomic: " + (System.currentTimeMillis() - startTime));
            System.out.println("atomic value: " + count2.intValue());
        });
        executor1.shutdown();
    }

    public synchronized void incInt() {
        count += 1;
    }

    public void incInt2() {
        count2.incrementAndGet();
    }


}
