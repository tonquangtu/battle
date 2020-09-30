
public class GrabTest {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {2, 6, 8, 5}));
        System.out.println(solution(new int[] {1, 5, 5, 2, 6}));
    }

    public static int solution(int[] blocks) {
        int len = blocks.length;
        if (len == 0) {
            return 0;
        }
        int[] higherLeft = new int[len];
        int[] higherRight = new int[len];

        higherLeft[0] = 0;
        for (int i  = 1; i < len; i++) {
            higherLeft[i] = blocks[i - 1] >= blocks[i] ? higherLeft[i - 1] : i;
        }

        higherRight[len - 1] = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            higherRight[i] = blocks[i + 1] >= blocks[i] ? higherRight[i + 1] : i;
        }

        int maxDistance = 0;
        for (int i = 0; i < len; i++) {
            maxDistance = Math.max(maxDistance, higherRight[i] - higherLeft[i] + 1);
        }
        return maxDistance;
    }
}
