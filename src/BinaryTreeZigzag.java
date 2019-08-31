/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class TStack {
    private List<TreeNode> data;
    private int size;

    public TStack() {
        this.data = new ArrayList<>(1000);
        this.size = 0;
    }

    public void push(TreeNode node) {
        this.data.add(node);
        size += 1;
    }

    public TreeNode pop() {
        size -= 1;
        return data.get(size);
    }

    public boolean empty() {
        return size <= 0;
    }
}


public class BinaryTreeZigzag {

    public static boolean R2L = false;

    public static boolean L2R = true;

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        boolean direction = L2R;
        if (root == null) {
            return result;
        }

        TStack stack = new TStack();
        stack.push(root);
        while (!stack.empty()) {
            List<Integer> level = new ArrayList<>();
            TStack childStack = new TStack();

            while (!stack.empty()) {
                TreeNode node = stack.pop();
                level.add(node.val);
                if (direction == R2L) {
                    if (node.right != null) {
                        childStack.push(node.right);
                    }
                    if (node.left != null) {
                        childStack.push(node.left);
                    }
                } else {
                    if (node.left != null) {
                        childStack.push(node.left);
                    }
                    if (node.right != null) {
                        childStack.push(node.right);
                    }
                }
            }
            direction = !direction;
            stack = childStack;
            result.add(level);
        }

        return result;
    }

    public static void main(String[] args) {
        var root = new TreeNode(3);
        var left = new TreeNode(9);
        var right = new TreeNode(20);
        root.left = left;
        root.right = right;

        right.left = new TreeNode(15);
        right.right = new TreeNode(7);

        var main = new BinaryTreeZigzag();
        var result = main.zigzagLevelOrder(root);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
