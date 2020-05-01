package LeetCode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author mizhu
 * @date 2020/5/1 09:41
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class _98ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(15);
        TreeNode right1 = new TreeNode(6);
        TreeNode right2 = new TreeNode(20);

//        long a = -2147483648;
//        int b= (int) a;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(Integer.MIN_VALUE);

//        root.left = left;
//        root.right = right;
//        right.left = right1;
//        right.right = right2;
        _98ValidateBinarySearchTree t = new _98ValidateBinarySearchTree();
        System.out.println(t.isValidBST(root));
    }

    /**
     * 中序遍历，比较前后的元素，有逆序则不是二叉搜索树
     *
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        // 注意节点大小为Integer.MIN_VALUE的情况
        long minValue = Long.MIN_VALUE;
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            // 先找最左子树
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                if (node.val <= minValue) {
                    return false;
                }
                minValue = node.val;
                node = node.right;
            }
        }

        return true;
    }

}
