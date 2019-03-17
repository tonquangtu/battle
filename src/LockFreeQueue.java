import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * tail --> hold next position for enqueue
 * head -> hold current position for dequeue
 * queue contain positive integer (> 0)
 * Hàng đợi có thể xoay vòng. tail -> (0, maxSize - 1), head -> (0, maxSize - 1)
 *
 */
public class LockFreeQueue {
    private int maxSize;
    private int [] list;
    private AtomicInteger head = new AtomicInteger(0);
    private AtomicInteger tail = new AtomicInteger(0);

    private AtomicInteger enqueue = new AtomicInteger(0);
    private AtomicInteger dequeue = new AtomicInteger(0);

    public LockFreeQueue(int maxSize) {
        this.maxSize = maxSize;
        list = new int[maxSize];
    }

    // put item into queue
    public boolean enqueue(int item) throws RuntimeException {
        while (dequeue.get() > 0);
        enqueue.incrementAndGet();
        try {
            int h = head.get();
            int t = tail.get();
            if (size(h, t) >= maxSize - 1) {
                return false;
            }
            int curr = tail.getAndIncrement();
            t = curr + 1;
            if (size(h, t) >= maxSize) {
                tail.decrementAndGet();
                return false;
            }
            curr = curr % maxSize;
            list[curr] = item;
            if (t >= maxSize) {
                tail.compareAndSet(t, t % maxSize);
            }
            return true;
        } finally {
            enqueue.decrementAndGet();
        }

    }
    // pool item from queue
    public int dequeue() throws RuntimeException {
        while (enqueue.get() > 0);
        dequeue.incrementAndGet();
        try {
            int h = head.get();
            int t = tail.get();
            if (h == t) {
                return -1;
            }
            int curr = head.getAndIncrement();
            // have concurrent dequeue here
            if (curr != h && (h < t && curr >= h)) {
                head.decrementAndGet();
                return -2;
            }
            h = curr + 1;
            curr = curr % maxSize;
            int item = list[curr];
            list[curr] = 0;
            if (h >= maxSize) {
                head.compareAndSet(t, t % maxSize);
            }
            return item;
        } finally {
            dequeue.decrementAndGet();
        }
    }

    public int size() {
        int h = head.get();
        int t = tail.get();
        return size(h, t);
    }

    public int size(int h, int t) {
        if (t == h) {
            return 0;
        } else if (t > h) {
            return t - h;
        } else {
            return maxSize + (t - h);
        }
    }
    public int [] unwrap() {
        return this.list;
    }
}

class TestLockFreeQueue {
    public static void main(String[] args) {
        var maxSize = 100;
        var queue = new LockFreeQueue(maxSize + 1);
        var tester = new TestLockFreeQueue();
        tester.testEnqueue(queue, maxSize, true);
//        tester.testDequeue(queue, maxSize, 5);
    }

    public void testEnqueue(LockFreeQueue queue, int maxSize, boolean testWithDequeue) {
        int thread = 5;
        int loop = 5;
        var executor = Executors.newFixedThreadPool(thread);
        int perLoop = maxSize / loop;
        for (int i = 1; i <= loop; i++) {
            int index = i;
            executor.submit(() -> {
                int start = (index - 1) * perLoop + 1;
                int end = index * perLoop + 1;
                for (int j = start; j < end; j++) {
                    var suces = queue.enqueue(j);
                    if (!suces) {
                        System.out.println("enqueue fail: " + j);
                    }
                }
                if (queue.size() >= maxSize) {
                    showQueue(queue);
                    if (testWithDequeue) {
                        testDequeue(queue, maxSize, maxSize + 30);
                    }
                }
                System.out.println("Enqueue start: " + start + ",end: " + end + "," + Thread.currentThread().getName() + ": " + queue.size());
            });

        }
        executor.shutdown();
    }

    public void testDequeue(LockFreeQueue queue,int maxSize, int totalLoop) {
        int thread = 5;
        int loop = 5;
        var executor = Executors.newFixedThreadPool(thread);
        int perLoop = totalLoop / loop;
        var dequeueResult = new ArrayList<Integer>();
        for (int i = 1; i <= loop; i++) {
            int index = i;
            executor.submit(() -> {
                int start = (index - 1) * perLoop + 1;
                int end = index * perLoop + 1;
                for (int j = start; j < end; j++) {
                    int res = queue.dequeue();
                    dequeueResult.add(res);
                }
                if (queue.size() == 0) {
                    showQueue(queue);
                    showDequeueResult(dequeueResult);
                }
                System.out.println("Dequeue start: " + start + ",end: " + end + "," + Thread.currentThread().getName() + ": " + queue.size());
            });
        }
        executor.shutdown();
    }

    public void showQueue(LockFreeQueue queue) {
        var list = queue.unwrap();
        var sBuilder = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            sBuilder.append(list[i]).append(", ");
        }
        System.out.println("Queue: " + sBuilder.toString());
    }

    public void showDequeueResult(ArrayList<Integer> result) {
        var sBuilder = new StringBuilder();
        for (int item : result) {
            sBuilder.append(item).append(", ");
        }
        System.out.println("DequeueResult: " + sBuilder.toString());

    }
}