package LeetCode;

/**
 * @author mizhu
 * @date 2020/8/9 16:26
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
public class _112PathSum {
    private int currentSum = 0;
    private int targetSum = 0;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        targetSum = sum;
        return doSumPath(root);
    }

    /**
     * 自己实现，能过，但是比较麻烦
     */
    private boolean doSumPath(TreeNode root) {
        currentSum += root.val;
        boolean validSum = false;
        if (root.left == null && root.right == null) {

            validSum = currentSum == targetSum;
            currentSum -= root.val;
            return validSum;
        }

        // 此处，若root.left == null，且root非叶子节点
        // 直接返回false即可
        if (root.left != null) {
            validSum = doSumPath(root.left);
        }

        if (validSum) {
            return true;
        }

        if (root.right != null) {
            validSum = doSumPath(root.right);
        }

        currentSum -= root.val;

        return validSum;
    }

    /**
     * 简单版本，加到sum，等效为sum减到0
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
        // 可以理解为，非叶子节点的左(右)孩子为空
        // 则不可能会左此处满足条件，此路不通
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return sum == root.val;
        }

        return hasPathSum1(root.left, sum - root.val)
                || hasPathSum1(root.right, sum - root.val);
    }

}
