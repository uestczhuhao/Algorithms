package leetcode.editor.cn;

public class _572SubtreeOfAnotherTree {
    public static void main(String[] args) {
        Solution t = new _572SubtreeOfAnotherTree().new Solution();
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
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (subRoot == null) {
                return true;
            }
            if (root == null) {
                return false;
            }
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot) || isSameTree(root, subRoot);
        }

        public boolean isSameTree(TreeNode node1, TreeNode node2) {
            if (node1 == null && node2 == null) {
                return true;
            }
            if (node1 == null || node2 == null) {
                return false;
            }
            return node1.val == node2.val && isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
