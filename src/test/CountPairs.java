package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountPairs {
    public static void main(String[] args) {
        System.out.println(countPairSumEqualsK(new int[] {2, 3, 2, 3, 2, 1, 4, 4, 1, 1}, 5));
        System.out.println(countPairSumEqualsK2(new int[] {2, 3, 2, 3, 2, 1, 4, 4, 1, 1}, 5));
    }

    public static int countPairSumEqualsK(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : arr) {
            if (!map.containsKey(num)) {
                if (map.containsKey(k - num)) {
                    count ++;
                }
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        return count;
    }

    public static int countPairSumEqualsK2(int[] arr, int k) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            int pos = Arrays.binarySearch(arr, i, arr.length, k - arr[i]);
            if (pos < i) {
                count ++;
            }
        }
        return count;
    }
}
