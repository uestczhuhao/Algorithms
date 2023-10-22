package leetcode.editor.cn;

public class _530MinimumAbsoluteDifferenceInBst {
    public static void main(String[] args) {
        Solution t = new _530MinimumAbsoluteDifferenceInBst().new Solution();
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        System.out.println(t.getMinimumDifference(root));
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
        int ans = Integer.MAX_VALUE, pre = Integer.MAX_VALUE;

        public int getMinimumDifference(TreeNode root) {
            midOrder(root);
            return ans;
        }

        // 中序遍历，同时获取当前值和上一个值的差，取差值的最小值即可
        private void midOrder(TreeNode node) {
            if (node == null) {
                return;
            }
            midOrder(node.left);
            if (pre != Integer.MAX_VALUE) {
                ans = Math.min(ans, node.val - pre);
            }
            pre = node.val;
            midOrder(node.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
