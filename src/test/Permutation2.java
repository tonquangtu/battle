package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Permutation2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] mark = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            mark[i] = false;
        }
        int[] item = new int[nums.length];
        doPermuteUnique(nums, res, item, 0, mark);
        return res;
    }

    void doPermuteUnique(int[] nums, List<List<Integer>> res, int[] item, int index, boolean[] mark) {
        if (index == nums.length) {
            List<Integer> resItem = new ArrayList<>();
            for (int val : item) {
                resItem.add(val);
            }
            res.add(resItem);
            return;
        }

        int prevChoosed = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == prevChoosed) {
                continue;
            }
            if (!mark[i]) {
                mark[i] = true;
                prevChoosed = nums[i];
                item[index] = nums[i];
                doPermuteUnique(nums, res, item, index + 1, mark);
                mark[i] = false;
            }
        }
    }
}