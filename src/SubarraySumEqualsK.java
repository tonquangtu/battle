import java.util.HashMap;
import java.util.Map;

/**
 * This link description: https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int [] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        int [] nums = {1, 1, 1};
        int k = 0;
        int result = new SubarraySumEqualsK().subarraySum(nums, k);
        System.out.println(result);
    }


    public int subarraySum(int[] nums, int k) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        int [] sums = new int[length + 1];
        sums[0] = 0;
        for (int i = 1; i <= length; i++) {
            sums[i] += sums[i - 1] + nums[i - 1];
        }

        // Now, we have sum array: 0, s1, s2 .. sn
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int result = 0;
        for (int i = 1; i <= length; i++) {
            int checkExist = sums[i] - k;
            Integer count = map.get(checkExist);
            if (count != null && count > 0) {
                result += count;
            }

            Integer numDuplicate = map.get(sums[i]);
            if (numDuplicate == null) {
                map.put(sums[i], 1);
            } else {
                map.put(sums[i], numDuplicate + 1);
            }
        }
        return result;
    }
}
