package inmemory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

public class TestConcurrentHashMap {
    Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) throws InterruptedException {
        TestConcurrentHashMap tester = new TestConcurrentHashMap();
        tester.testConcurrentAddValue();
        tester.testNoConcurrentAddValue();
    }

    public void testConcurrentAddValue() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        String key = "tuton";
        int n = 10000;
        for (int i = 0; i < n; i++) {
            executorService.submit(() -> {
                concurrentIncrement(key);
            });
        }
        Thread.sleep(2000);
        System.out.println(map.get(key));
        executorService.shutdown();
    }

    public void testNoConcurrentAddValue() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        String key = "tuton";
        int n = 10000;
        for (int i = 0; i < n; i++) {
            executorService.submit(() -> {
                increment(key);
            });
        }
        Thread.sleep(2000);
        System.out.println(map.get(key));
        executorService.shutdown();
    }

    public Integer concurrentIncrement(String key) {
        return map.compute(key, (s, integer) -> {
            if (integer == null) {
                return 1;
            }
            return integer + 1;
        });
    }

    public Integer increment(String key) {
        Integer oldValue = map.get(key);
        int newValue;
        if (oldValue == null) {
            newValue = 1;
        } else {
            newValue = oldValue + 1;
        }
        return map.put(key, newValue);
    }
}
