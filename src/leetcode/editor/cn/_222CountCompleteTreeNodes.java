package leetcode.editor.cn;

public class _222CountCompleteTreeNodes {
    public static void main(String[] args) {
        Solution t = new _222CountCompleteTreeNodes().new Solution();
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
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return countNodes(root.left) + 1 + countNodes(root.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
