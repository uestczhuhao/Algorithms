package leetcode;

/**
 * @author mizhu
 * @date 2020/7/7 22:30
 * <p>
 * 给你一棵二叉树，它的根为 root 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
 * <p>
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,6]
 * 输出：110
 * 解释：删除红色的边，得到 2 棵子树，和分别为 11 和 10 。它们的乘积是 110 （11*10）
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,3,4,null,null,5,6]
 * 输出：90
 * 解释：移除红色的边，得到 2 棵子树，和分别是 15 和 6 。它们的乘积为 90 （15*6）
 * 示例 3：
 * <p>
 * 输入：root = [2,3,9,10,7,8,6,5,4,11,1]
 * 输出：1025
 * 示例 4：
 * <p>
 * 输入：root = [1,1]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 每棵树最多有 50000 个节点，且至少有 2 个节点。
 * 每个节点的值在 [1, 10000] 之间。
 */
public class _1339MaximumProductOfSplittedBinaryTree {
    long sum = 0;
    long closetMid = 0;

    public int maxProduct(TreeNode root) {
        if (root == null) {
            return 0;
        }
        computeSum(root);
        findClosetMis(root);
        return (int) ((closetMid * (sum - closetMid))  % 1000000007);
    }

    public void computeSum(TreeNode root) {
        if (root == null) {
            return;
        }

        sum += root.val;
        computeSum(root.left);
        computeSum(root.right);
    }

    public long findClosetMis(TreeNode root) {
        if (root == null) {
            return 0;
        }

        long left = findClosetMis(root.left);
        long right = findClosetMis(root.right);
        long currentSum = left + right + root.val;
        if (Math.abs(sum - currentSum*2) < Math.abs(sum - closetMid*2)) {
            closetMid = currentSum;
        }
        return currentSum;
    }

    public static void main(String[] args) {
        _1339MaximumProductOfSplittedBinaryTree t = new _1339MaximumProductOfSplittedBinaryTree();
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);
        TreeNode root6 = new TreeNode(6);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        root3.left = root6;
        System.out.println(t.maxProduct(root1));
    }
}
