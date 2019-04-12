/**
 * https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {
    // bruce force
    public int maxArea(int[] height) {
        int max = 0;
        int tmpArea;
        int n = height.length;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                tmpArea = Math.min(height[i], height[j]) * (i - j);
                if (tmpArea > max) {
                    max = tmpArea;
                }
            }
        }
        return max;
    }

    // start with 2 left and right edges and move forward to inside to calculate area
    // because area = min (left_edge, right_edge) * (right - left) --> if left_edge < right_edge
    // we move to inside by left edge and vise versa
    public int maxArea2(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        int tmpHeight;
        int tmpWidth;
        int tmpArea;
        while (left < right) {
            tmpWidth = right - left;
            if (height[left] < height[right]) {
                tmpHeight = height[left];
                left ++;
            } else {
                tmpHeight = height[right];
                right --;
            }
            tmpArea = tmpHeight * tmpWidth;
            if (tmpArea > max) {
                max = tmpArea;
            }
//            tmpArea = Math.min(height[left], height[right]) * (right - left);
//            if (tmpArea > max) {
//                max = tmpArea;
//            }
//            if (height[right] < height[left]) {
//                right --;
//            } else {
//                left ++;
//            }
        }
        return max;
    }
}

class TestContainerWithMostWater {
    public static void main(String[] args) {
        int [] input = new int [] {1,8,6,2,5,4,8,3,7};
        var expected = 49;

        var tester = new ContainerWithMostWater();
        var res  = tester.maxArea2(input);
        System.out.println(res);



    }
}
