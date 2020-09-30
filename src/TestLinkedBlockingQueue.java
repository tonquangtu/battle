import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestLinkedBlockingQueue {
    public void test() {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        queue.size();
        queue.poll();
    }
}
