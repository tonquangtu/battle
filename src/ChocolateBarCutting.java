
/**
 * We try to searching from min -> average to find the maximum sweetness level you can have
 * Use Greedy, a simple version (not use binary search)
 */
public class ChocolateBarCutting {

    public static void main(String[] args) {
        var cutter = new ChocolateBarCutting();
        System.out.println(cutter.cuttingChocolate(new int[] {1, 2, 3, 3, 2, 4, 4}, 3)); // expect 5
        System.out.println(cutter.cuttingChocolate(new int[] {1, 2, 3, 3, 4, 4}, 3)); // expect 4
        System.out.println(cutter.cuttingChocolate(new int[] {3, 4, 5, 3, 3, 2}, 3)); // expect 5
        System.out.println(cutter.cuttingChocolate(new int[] {2, 2, 2, 2, 2, 2}, 6)); // expect 2
        System.out.println(cutter.cuttingChocolate(new int[] {1, 2, 3, 4, 5, 6}, 4)); // expect 4
        System.out.println(cutter.cuttingChocolate(new int[] {1, 2, 3, 4, 5, 6}, 3)); // expect 6
        System.out.println(cutter.cuttingChocolate(new int[] {1, 4, 0, 4, 4, 9}, 2)); // expect 9
        System.out.println(cutter.cuttingChocolate(new int[] {1, 4, 0, 4, 4, 9}, 4)); // expect 4
        System.out.println(cutter.cuttingChocolate(new int[] {1, 3, 3, 3, 0, 30}, 3)); // expect 4
    }

    public int cuttingChocolate(int[] bar, int k) {
        int sum = 0;
        int min = 0;
        for (int value : bar) {
            sum += value;
            if (min > value) {
                min = value;
            }
        }

        int maxSweet = 0;
        int avg = sum / k;
        for (int sweet = min; sweet <= avg; sweet ++) {
            if (tryCut(sweet, bar, k)) {
                maxSweet = sweet;
            }
        }
        return maxSweet;
    }

    // greedy to count maximum number of pieces can cut that each piece have sweetness level >= sweet
    public boolean tryCut(int sweet, int[] bar, int k) {
        for (int i = 0; i < bar.length; i++) {
            int maxPieceCanCut = countLeft(bar, i, sweet) + countRight(bar, i, sweet);
            if (maxPieceCanCut >= k) {
                return true;
            }
        }
        return false;
    }

    public int countLeft(int[] bar, int pos, int sweet) {
        int count = 0;
        int sum = 0;
        for (int i = pos; i >= 0; i--) {
            sum += bar[i];
            if (sum >= sweet) {
                count += 1;
                sum = 0;
            }
        }
        return count;
    }

    public int countRight(int[] bar, int pos, int sweet) {
        int count = 0;
        int sum = 0;
        for (int i = pos + 1; i < bar.length; i++) {
            sum += bar[i];
            if (sum >= sweet) {
                count += 1;
                sum = 0;
            }
        }
        return count;
    }
}
