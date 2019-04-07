public class MedianOfTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return median(nums2);
        }
        if (nums2.length == 0) {
            return median(nums1);
        }
        int [] shortArr;
        int [] longArr;
        if (nums1.length > nums2.length) {
            shortArr = nums2;
            longArr = nums1;
        } else {
            shortArr = nums1;
            longArr = nums2;
        }

        int i = 0, j = 0;
        int mini = 0;
        int maxi = shortArr.length;
        int shortLength = shortArr.length;
        int longLength = longArr.length;

        while (i >= 0 && i < shortLength) {
            i = (mini + maxi) / 2;
            j = (shortLength + longLength + 1)/2 - i;
            if ((i == 0 || j >= longLength || shortArr[i - 1] <= longArr[j])
                    && (j == 0 || i >= shortLength || longArr[j - 1] <= shortArr[i])) {
                break;
                // find i, j
            }
            else if (i > 0 && j < longLength && shortArr[i - 1] > longArr[j]) {
                maxi = i - 1;
            } else {
                mini = i + 1;
            }
        }
        int maxLeft, minRight;
        if (i == 0) {
            maxLeft = longArr[j - 1];
        } else if (j == 0) {
            maxLeft = shortArr[i - 1];
        } else {
            maxLeft = Math.max(shortArr[i - 1], longArr[j - 1]);
        }
        // if total size is odd, we just care about left side. Pick the max of elements in left side
        if ((shortLength + longLength) % 2 != 0) {
            return maxLeft;
        }

        // otherwise, if total size is even, we need to care both left size and right size.
        // pick the maximum of elements in left side
        // lick the minimum of elements in right side
        if (i == shortLength) {
            minRight = longArr[j];
        } else if (j == longLength) {
            minRight = shortArr[i];
        } else {
            minRight = Math.min(shortArr[i], longArr[j]);
        }
        return (maxLeft + minRight) / 2.0;
    }

    public double median(int [] nums) {
        if (nums.length % 2 == 0) {
            return (nums[nums.length/2] + nums[nums.length/2 - 1])/2.0;
        } else {
            return nums[nums.length/2];
        }
    }
}

class TestMedianOfTwoSortedArray {
    public static void main(String[] args) {
        var medianer = new MedianOfTwoSortedArray();
        var result = 0.0;
        // 3.5
        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {3, 4, 5, 6};
        result = medianer.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);

        // 2
        int [] nums11 = {1, 3};
        int [] nums12 = {2};
        result = medianer.findMedianSortedArrays(nums11, nums12);
        System.out.println(result);

        // 2
        int [] nums21 = {1};
        int [] nums22 = {3};
        result = medianer.findMedianSortedArrays(nums21, nums22);
        System.out.println(result);

        // 1
        int [] nums31 = {};
        int [] nums32 = {1};
        result = medianer.findMedianSortedArrays(nums31, nums32);
        System.out.println(result);

        // -1
        int [] nums41 = {3};
        int [] nums42 = {-2, -1};
        result = medianer.findMedianSortedArrays(nums41,  nums42);
        System.out.println(result);

    }
}
