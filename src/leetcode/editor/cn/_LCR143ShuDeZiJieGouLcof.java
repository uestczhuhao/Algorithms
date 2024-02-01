package leetcode.editor.cn;

public class _LCR143ShuDeZiJieGouLcof {
    public static void main(String[] args) {
        Solution t = new _LCR143ShuDeZiJieGouLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        /**
         * 由定义，空树不是子树，因此当B==null时，直接返回false；又由于B不为null，所以当A为null时，也直接返回false，因此合并为A、B都不为null
         * 有三种情况可以认为B是A序列的子序列
         * 1. A树是否包含B树，即recur(root, subRoot)，
         * 2. B是否是A.left的子序列，即isSubStructure(root.left, subRoot)
         * 3. B是A.right的子序列，即isSubStructure(root.right, subRoot)
         * 其中recur是判断A树是否包含B树（即A树的某个子结构是否和B树长得一样）
         * 1. 当B==null时，不管A是否已经到叶子节点的下一层，都认为包含，返回true
         * 2. 当A==null && subRoot != null时，A肯定不包含B，返回false
         * 3. 当且仅当 root.val == subRoot.val && root 的左子树包含B的左子树 && A的右子树包含B的右子树才可以
         *
         *
         */
        public boolean isSubStructure(TreeNode root, TreeNode subRoot) {
            if (subRoot == null || root == null) {
                return false;
            }

            return recur(root, subRoot) || isSubStructure(root.left, subRoot) || isSubStructure(root.right, subRoot);
        }

        public boolean recur(TreeNode A, TreeNode B) {
            if (B == null) {
                return true;
            }
            if (A == null) {
                return false;
            }
            return A.val == B.val && recur(A.left, B.left) && recur(A.right, B.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
