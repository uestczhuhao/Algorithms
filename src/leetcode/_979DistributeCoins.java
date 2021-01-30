package leetcode;

/**
 * @author mizhu
 * @date 20-6-25 下午9:13
 * <p>
 * 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。
 * <p>
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * <p>
 * 返回使每个结点上只有一枚硬币所需的移动次数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：[3,0,0]
 * 输出：2
 * 解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：[0,3,0]
 * 输出：3
 * 解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：[1,0,2]
 * 输出：2
 * 示例 4：
 * <p>
 * <p>
 * <p>
 * 输入：[1,0,0,null,3]
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1<= N <= 100
 * 0 <= node.val <= N
 */
public class _979DistributeCoins {
    int steps = 0;

    public int distributeCoins(TreeNode root) {
        if (root == null) {
            return -1;
        }

        overloadCompute(root);
        return steps;
    }

    /**
     * 计算节点node的超载数量
     * 即其左右子树和其本身的金币数-1
     */
    public int overloadCompute(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSteps = overloadCompute(node.left);
        int rightSteps = overloadCompute(node.right);
        steps += Math.abs(leftSteps) + Math.abs(rightSteps);

        // 对以node为根节点的子树，若left 和 right的超载量相反
        // 则其子树内就能相互移动，这颗子树的超载量可以抵消
        // 但上诉的移动过程需要记录下来
        return node.val + leftSteps + rightSteps - 1;
    }
}
