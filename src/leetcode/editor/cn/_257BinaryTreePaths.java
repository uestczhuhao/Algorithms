package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _257BinaryTreePaths {
    public static void main(String[] args) {
        Solution t = new _257BinaryTreePaths().new Solution();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node5;
        System.out.println(t.binaryTreePaths(node1));
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
        List<String> ans = new ArrayList<>();

        public List<String> binaryTreePaths(TreeNode root) {
            dfs(root, new LinkedList<>());
            return ans;
        }

        public void dfs(TreeNode node, List<Integer> path) {
            path.add(node.val);
            if (node.left == null && node.right == null) {
                int i = 0;
                StringBuilder sb = new StringBuilder();
                for (; i < path.size() - 1; i++) {
                    sb.append(path.get(i)).append("->");
                }
                sb.append(path.get(i));
                ans.add(sb.toString());
            }
            if (node.left != null) {
                dfs(node.left, path);
            }
            if (node.right != null) {
                dfs(node.right, path);
            }
            path.remove(path.size() - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
