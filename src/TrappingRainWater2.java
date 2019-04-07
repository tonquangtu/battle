/**
 * https://leetcode.com/problems/trapping-rain-water
 * trapCount = sum (maximum unit water for each column can trap)
 * maximum unit water can trap for each column =  min (max height left, max height right) - height of this column
 *
 */

public class TrappingRainWater2 {

    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int trapCount = 0;
        int length = height.length;
        int[] maxLeft = new int[length];
        int[] maxRight = new int[length];

        int maxIndex = length - 1;
        maxLeft[0] = height[0];
        maxRight[maxIndex] = height[maxIndex];
        for (int i = 1; i < maxIndex; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
            int rightPos = maxIndex - i;
            maxRight[rightPos] = Math.max(maxRight[rightPos + 1], height[rightPos]);
        }

        for (int i = 1; i < maxIndex; i++) {
            trapCount += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }
        return trapCount;
    }
}

class TestTrapper2 {
    public static void main(String[] args) {
        var input = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
//        var input = new int[]{5, 4, 1, 2};
        var trapper = new TrappingRainWater2();
        var result = trapper.trap(input);
        System.out.println(result);
    }
}
