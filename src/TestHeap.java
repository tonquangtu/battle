import java.util.PriorityQueue;

public class TestHeap {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        PriorityQueue<Double> maxHeap = new PriorityQueue<>((i1, i2) -> {
            return i2 > i1 ? 1 : (!i2.equals(i1) ? -1 : 0);
        });

        maxHeap.add(4.0);
        maxHeap.add(5.0);
        maxHeap.add(3.0);
        maxHeap.add(9.0);


        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
}
