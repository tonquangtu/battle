import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestJavaExecutors {
    public static void main(String[] args) {
        var tester = new TestJavaExecutors();
        tester.testDelayExecutor();

        System.out.println(UUID.randomUUID().toString());

        Instant instant = Instant.now();
        System.out.println(instant.toString());
        System.out.println(Instant.ofEpochSecond(instant.getEpochSecond()));



    }

    public void testDelayExecutor() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("anh tu");
            }
        }, 0, TimeUnit.SECONDS);
    }
}
