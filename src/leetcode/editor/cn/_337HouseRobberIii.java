package leetcode.editor.cn;

//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。 
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。 
//
// 示例 1: 
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7. 
//
// 示例 2: 
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
// 
// Related Topics 树 深度优先搜索 
// 👍 709 👎 0


import java.util.Objects;

public class _337HouseRobberIii {
    public static void main(String[] args) {
        Solution t = new _337HouseRobberIii().new Solution();
    }

    /**

     */
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
         * https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
         * 思路：对节点n，只有偷或不偷两种
         * 1 若不偷，当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
         * 2 若偷，当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
         */
        public int rob(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int[] result = doRob(root);
            return Math.max(result[0], result[1]);
        }

        /**
         * 返回node节点能获取的钱数数组
         * result[0] 不偷，当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
         * result[1] 偷，当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
         */
        private int[] doRob(TreeNode node) {
            if (node == null) {
                return new int[]{0, 0};
            }

            int[] leftChildCome = doRob(node.left);
            int[] rightChildCome = doRob(node.right);
            int noSelect = Math.max(leftChildCome[0], leftChildCome[1]) + Math.max(rightChildCome[0], rightChildCome[1]);
            int select = leftChildCome[0] + rightChildCome[0] + node.val;

            return new int[]{noSelect, select};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TreeNode)) {
            return false;
        }
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val &&
            Objects.equals(left, treeNode.left) &&
            Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}