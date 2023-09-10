package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _199BinaryTreeRightSideView {
    public static void main(String[] args) {
        Solution t = new _199BinaryTreeRightSideView().new Solution();
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
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            if (root == null) {
                return ans;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                    if (i == size - 1) {
                        ans.add(cur.val);
                    }
                }
            }
            return ans;
        }

        public List<Integer> rightSideView1(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            if (root == null) {
                return ans;
            }
            List<TreeNode> curLevel = new ArrayList<>();
            List<TreeNode> nextLevel = new ArrayList<>();
            curLevel.add(root);
            while (!curLevel.isEmpty()) {
                for (int i = 0; i < curLevel.size(); i++) {
                    if (i == curLevel.size() - 1) {
                        ans.add(curLevel.get(i).val);
                    }
                    TreeNode cur = curLevel.get(i);
                    if (cur.left != null) {
                        nextLevel.add(cur.left);
                    }
                    if (cur.right != null) {
                        nextLevel.add(cur.right);
                    }
                }
                curLevel = nextLevel;
                nextLevel = new ArrayList<>();
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
