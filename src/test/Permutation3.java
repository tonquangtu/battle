package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation3 {

    private static final int MAX_PRIME_LEN = 2;

    public static void main(String[] args) {
        Permutation3 permutation3 = new Permutation3();
        //generatePrimeShopee.generatePrimes(new int[] {2, 1, 3});
        permutation3.generatePrimes(new int[] {1, 1, 3});
    }

    List<Integer> generatePrimes(int[] nums) {
        Set<Integer> res = new HashSet<>();
        int[] placeholder = new int[nums.length];
        boolean[] mark = new boolean[nums.length];
        doGeneratePrimes(nums, res, placeholder, mark, 0);
        for (Integer num : res) {
            System.out.println(num);
        }
        return new ArrayList<>(res);
    }

    void doGeneratePrimes(int[] nums, Set<Integer> res, int[] placeholder, boolean[] mark, int index) {
        if (index >= nums.length || index > MAX_PRIME_LEN) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!mark[i]) {
                mark[i] = true;
                placeholder[index] = nums[i];
                checkPrimeAndAddIfPass(res, placeholder, index);
                doGeneratePrimes(nums, res, placeholder, mark, index + 1);
                mark[i] = false;
            }
        }
    }

    void checkPrimeAndAddIfPass(Set<Integer> res, int[] placeholder, int index) {
        int num = 0;
        for (int i = 0; i <= index; i++) {
            num += placeholder[index - i] * Math.pow(10, i);
        }
        if (isPrime(num)) {
            res.add(num);
        }
    }

    boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        int sqrt = (int)Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % 2 == 0) {
                return false;
            }
        }
        return true;
    }

}
