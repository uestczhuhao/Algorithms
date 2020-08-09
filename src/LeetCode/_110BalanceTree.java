package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mizhu
 * @date 2020/8/9 09:25
 */
public class _110BalanceTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return confirmHeightAndBalance(root) != -1;
    }

    /**
     * 计算二叉树的高度，返回有两种
     * 1. 该树为平衡的，返回高度
     * 2. 该树非平衡，返回-1
     */
    private int confirmHeightAndBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = confirmHeightAndBalance(node.left);
        if (left == -1) {
            return -1;
        }

        int right = confirmHeightAndBalance(node.right);
        if (right == -1) {
            return -1;
        }

        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

}
