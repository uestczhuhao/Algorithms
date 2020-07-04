package LeetCode;

import java.util.*;

/**
 * @author mizhu
 * @date 2020/7/4 16:26
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class _144BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> preOrderList = new ArrayList<>();
        Deque<TreeNode> nodeStack = new LinkedList<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            preOrderList.add(node.val);
            if (node.right != null) {
                nodeStack.push(node.right);
            }

            if (node.left != null) {
                nodeStack.push(node.left);
            }
        }

        return preOrderList;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        TreeNode right1 = new TreeNode(2);
        root.left = left;
        root.right = right;
        right.left = right1;
        _144BinaryTreePreorderTraversal t = new _144BinaryTreePreorderTraversal();
        System.out.println(t.preorderTraversal(root));
    }
}
