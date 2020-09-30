import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, Integer::sum);
        }
        PriorityQueue<Integer[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer[] item = new Integer[] {entry.getKey(), entry.getValue()};
            if (minHeap.size() < k) {
                minHeap.add(item);
            } else {
                Integer[] minItem = minHeap.peek();
                if (minItem[1] < item[1]) {
                    minHeap.poll();
                    minHeap.add(item);
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (Integer[] item : minHeap) {
            res.add(item[0]);
        }
        return res;
    }
}
