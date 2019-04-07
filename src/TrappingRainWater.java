/**
 * https://leetcode.com/problems/trapping-rain-water
 */

public class TrappingRainWater {

    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int totalTrap = 0;
        int prevHIndex = 0;
        int totalHeight = 0;
        for (int i = 1; i < height.length - 1; i++) {
            if (height[i] < height[prevHIndex] && height[i] < height[i + 1]) {
                totalHeight += height[i];
                totalTrap += calculateTrap(height, prevHIndex, i + 1);
                if (height[i + 1] > height[prevHIndex]) {
                    prevHIndex = i + 1;
                }
            } else if (height[i] > height[prevHIndex]) {
                totalHeight = 0;
                prevHIndex = i;
            }
        }

        return totalTrap;
    }

    private int calculateTrap(int [] height, int prevHIndex, int currHIndex) {
        int maxHCanTrap = height[prevHIndex];
        if (maxHCanTrap > height[currHIndex]) {
            maxHCanTrap = height[currHIndex];
        }
        int trapCount = 0;
        for (int i = prevHIndex + 1; i < currHIndex; i++) {
            if (height[i] < maxHCanTrap) {
                trapCount += (maxHCanTrap - height[i]);
                height[i] = maxHCanTrap;
            }
        }
        return trapCount;
    }
}

class TestTrapper {
    public static void main(String[] args) {
//        var input = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        var input = new int [] {5, 4, 1, 2};
        var trapper = new TrappingRainWater();
        var result = trapper.trap(input);
        System.out.println(result);
    }
}
