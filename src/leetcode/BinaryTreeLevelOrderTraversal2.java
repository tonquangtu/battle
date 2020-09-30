package leetcode;

import java.util.ArrayList;
import java.util.List;


/**
 * https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/544/week-1-july-1st-july-7th/3378/
 */
public class BinaryTreeLevelOrderTraversal2 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        bottomTraversal(root, res);
        return res;
    }

    private void bottomTraversal(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return;
        }


    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

}
