package leetcode;

/**
 * @author zhuhao3@xiaomi.com
 * @date 2020/3/7 21:20
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 *
 */
public class _124MaximumPathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(-1);
        TreeNode right = new TreeNode(3);
        root.left = left;
//        root.right = right;
        _124MaximumPathSum t = new _124MaximumPathSum();
        System.out.println(t.maxPathSum(root));
    }

    int max = Integer.MIN_VALUE;
    /**
     * 思路：递归解法，从root开始，定义以node为顶点的节点值为maxSum(node)
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (null == root) {
            return 0;
        }

        maxSum(root);
        return max;
    }

    public int maxSum(TreeNode node) {
        if (null == node) {
            return 0;
        }

        // 左子树或右子树可能情况
        // 1 空树，则leftMax/rightMax为0
        // 2 非空，若左侧或右侧子树的最大值小于0，则可以安全的舍弃，因为加上只会使和更小
        int leftMax = Math.max(maxSum(node.left), 0);
        int rightMax = Math.max(maxSum(node.right), 0);

        // 更新max的值
        max = Math.max( max, node.val + leftMax + rightMax);

        // 返回以node为顶的最大值，此时node的左右子树已做剪枝处理（若子树最大和小于0则舍弃）
        // 注意返回值，只能是一个单一路径，不能有岔路，因此不能左右子节点都选
        return node.val + Math.max(leftMax, rightMax);
    }

}
