package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>给出一棵二叉树，其上每个结点的值都是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>&nbsp;。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。</p>
 *
 * <ul>
 * <li>例如，如果路径为&nbsp;<code>0 -&gt; 1 -&gt; 1 -&gt; 0 -&gt; 1</code>，那么它表示二进制数&nbsp;<code>01101</code>，也就是&nbsp;<code>13</code>&nbsp;。</li>
 * </ul>
 *
 * <p>对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。</p>
 *
 * <p>返回这些数字之和。题目数据保证答案是一个 <strong>32 位 </strong>整数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2019/04/04/sum-of-root-to-leaf-binary-numbers.png" />
 * <pre>
 * <strong>输入：</strong>root = [1,0,1,0,1,0,1]
 * <strong>输出：</strong>22
 * <strong>解释：</strong>(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [0]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中的节点数在&nbsp;<code>[1, 1000]</code>&nbsp;范围内</li>
 * <li><code>Node.val</code>&nbsp;仅为 <code>0</code> 或 <code>1</code>&nbsp;</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 197</li><li>👎 0</li></div>
 */

public class _1022SumOfRootToLeafBinaryNumbers {
    public static void main(String[] args) {
        Solution t = new _1022SumOfRootToLeafBinaryNumbers().new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        System.out.println(t.sumRootToLeaf(root));
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
        int ans = 0;

        public int sumRootToLeaf1(TreeNode root) {
            LinkedList<Integer> path = new LinkedList<>();
            path.add(root.val);
            dfs(root, path);
            return ans;
        }

        private void dfs(TreeNode root, LinkedList<Integer> path) {
            if (root.left == null && root.right == null) {
                int cur = 0;
                int multi = 1;
                for (int i = path.size() - 1; i >= 0; i--) {
                    cur += path.get(i) * multi;
                    multi *= 2;
                }
                ans += cur;
                return;
            }
            if (root.left != null) {
                path.add(root.left.val);
                dfs(root.left, path);
                path.removeLast();
            }

            if (root.right != null) {
                path.add(root.right.val);
                dfs(root.right, path);
                path.removeLast();
            }
        }

        public int sumRootToLeaf(TreeNode root) {
            dfs1(root, 0);
            return ans;
        }

        /**
         * 用递归到当前时的累加值cur代替path，每递归一层，将cur左移一位，并加上当前node的值
         */
        private void dfs1(TreeNode node, int cur) {
            int nCur = (cur << 1) + node.val;
            if (node.left == null && node.right == null) {
                ans += nCur;
                return;
            }
            // 该方法有隐含的add和remove
            // 每次进入递归下一层之前，把cur加上node的值，递归返回时，cur还是原来的值
            if (node.left != null) {
                dfs1(node.left, nCur);
            }

            if (node.right != null) {
                dfs1(node.right, nCur);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
