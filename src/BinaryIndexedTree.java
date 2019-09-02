import java.util.Arrays;

/**
 * It also called is Fenwick Tree
 * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/fenwick-binary-indexed-trees/tutorial/
 * https://www.topcoder.com/community/competitive-programming/tutorials/binary-indexed-trees/
 * Noted that: If x presented as a1b --> -x = (a1b)- --> last position of bit 1, 2^r = x&-x
 */
class BinaryIndexedTree {

    private int size;

    /**
     * store elements value of tree
     */
    private int[] nums;
    /**
     * short name of binary indexed tree, this array store sum of several elements in nums array
     * bit[i] = nums[i] + ... + nums[i - 2^r + 1] with 2^r = i&-i
     */
    private int[] bit;


    public BinaryIndexedTree(int size) {
        initTree(size);
    }

    private void initTree(int size) {
        this.size = size;
        this.nums = new int[size + 1];
        this.bit = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            nums[i] = 0;
            bit[i] = 0;
        }
    }

    /**
     * Update value for index position
     * Noted that: range of index from 1 -> size (both included)
     * When we update value at index i we must to update several elements of bit array
     * Take a look to analyze what indexes we will update for bit array
     *
     * sumAtK = query(k) = (nums[1] + ... + nums[k - 2^r]) + (nums[k - 2^r + 1] + ... + nums[k])
     *        = query(k - 2^r) + bit[k]
     *
     * --> vs i = k - 2^r + 1: query(k) = query(i - 1) + (nums[i] + ... + nums[k])
     *                                  = query(i - 1) + bit[k] vs k >= i and 2^r = k&-k
     *
     * --> update nums[i] --> need to update these bit[k] vs k = i + 2^r - 1
     *
     * Because k >= i --> 2^r run from i&-i, (i+1)&-(i+1), ...
     *
     * --> How to find suitable k like that :D
     * We have: k >= i
     * --> we try case by case:
     * with 2^r = i&-i                --> k = i + 1 - 1 = i --> i = i --> true --> k = i
     * with 2^r = (i+1)&-(i+1)        --> i + 1 ?= i + (i+1)&-(i+1) - 1 --> if true --> k = i + 1 else false --> next
     *  ....
     */
    public void update(int i, int val) {
        int delta = val - nums[i]; // we plus delta in elements in bit array corresponding with index i
        nums[i] = val;
        for (; i <= size; i += i&-i) {
            bit[i] += delta;
        }
    }

    /**
     * Query sum of elements from 1 -> i (both included)
     * sum = nums[1] + .... + nums[i]
     * = (nums[1] + ... + nums[i - 2^r]) + (nums[i - 2^r + 1] + ... + nums[i])
     * = query(i - 2^r) + bit[i]
     * = ....
     * Noted that: 2^r = i&-i with r is last position of bit 1 in i
     * bit[i] stores sum elements from i -> i - 2^r + 1 (both included) --> bit[i] = nums[i] + ..+ nums[i - 2^r + 1]
     * Time complexity is log(index)
     * @param i : index to query sum
     * @return sum(nums[1] -> nums[i])
     */
    public int query(int i) {
        int sum = 0;
        for (; i > 0; i -= i&-i) {
            sum += bit[i];
        }
        return sum;
    }

    public void printTree() {
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(bit));
    }

    public static void main(String[] args) {
        int size = 16;
        var tree = new BinaryIndexedTree(size);
        for (int i = 1; i <= size; i++) {
            tree.update(i, i);
        }
        tree.printTree();
        System.out.println(tree.query(10));
        System.out.println(tree.query(12));
        System.out.println(tree.query(15));
        System.out.println(tree.query(16));
    }
}
