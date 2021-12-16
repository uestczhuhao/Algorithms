package leetcode.editor.cn;

//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 10⁵] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 641 👎 0


import java.util.Deque;
import java.util.LinkedList;

public class _111MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution t = new _111MinimumDepthOfBinaryTree().new Solution();
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
        int minDepth = Integer.MAX_VALUE;

        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            dfs(root, 1);
            return minDepth;
        }

        private void dfs(TreeNode node, int depth) {
            if (node.left == null && node.right == null) {
                minDepth = Math.min(depth, minDepth);
                return;
            }

            if (node.left != null) {
                dfs(node.left, depth + 1);
            }

            if (node.right != null) {
                dfs(node.right, depth + 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}