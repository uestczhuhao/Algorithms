package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _637AverageOfLevelsInBinaryTree {
    public static void main(String[] args) {
        Solution t = new _637AverageOfLevelsInBinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            List<Double> ans = new ArrayList<>();
            while (!queue.isEmpty()) {
                int size = queue.size();
                long total = 0, count = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    total += cur.val;
                    count++;
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }
                ans.add(1.0 * total / count);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
