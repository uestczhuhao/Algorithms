package LeetCode;

/**
 * @author mizhu
 * @date 2020/8/16 11:18
 * <p>
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 1
 * / \
 * 2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 * <p>
 * 输入: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class _129SumRoot2LeafNumbers {

    int totalSum = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        computePathSum(root, 0);
        return totalSum;
    }

    private void computePathSum(TreeNode node, int currentSum) {
        currentSum = currentSum * 10 + node.val;
        if (node.left == null && node.right == null) {
            totalSum += currentSum;
            return;
        }

        if (node.left != null) {
            computePathSum(node.left, currentSum);
        }

        if (node.right != null) {
            computePathSum(node.right, currentSum);
        }
    }

    public static void main(String[] args) {
        _129SumRoot2LeafNumbers t = new _129SumRoot2LeafNumbers();
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        root1.left = root2;
        root1.right = root3;
        System.out.println(t.sumNumbers(root1));
    }

}
