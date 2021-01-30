package leetcode;

import java.util.*;

/**
 * @author mizhu
 * @date 2020/7/4 10:34
 * <p>
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,null,null,2]
 * <p>
 *    1
 *   /
 *  3
 *   \
 *    2
 * <p>
 * 输出: [3,1,null,null,2]
 * <p>
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 * <p>
 * 输入: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 *    /
 *   2
 * <p>
 * 输出: [2,1,4,null,null,3]
 * <p>
 * 2
 * / \
 * 1   4
 *    /
 *  3
 * 进阶:
 * <p>
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 */
public class _99RecoverBinarySearchTree {
    /**
     * 思路：
     * 1. 中序遍历，找到逆序对（一对或两对）
     * 2. 找到后交换两个找到对逆序对节点
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        // 前置节点
        TreeNode pred = null;
        // 一遍中序遍历，找出逆序对
        // 然后交换两个逆序位置节点的值
        TreeNode src = null;
        TreeNode tgt = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            if (pred != null) {
                if (node.val < pred.val) {
                    tgt = node;

                    if (src == null) {
                        src = pred;
                    } else {
                        break;
                    }
                }
            }

            pred = node;
            node = node.right;
        }

        if (src == null) {
            return;
        }
        int tmp = src.val;
        src.val = tgt.val;
        tgt.val = tmp;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        TreeNode right1 = new TreeNode(2);
        root.left = left;
        root.right = right;
        right.left = right1;

        _99RecoverBinarySearchTree t = new _99RecoverBinarySearchTree();
        t.recoverTree(root);
    }
}
