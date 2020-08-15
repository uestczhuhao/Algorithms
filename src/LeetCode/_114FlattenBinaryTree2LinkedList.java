package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 2020/8/15 21:58
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *  
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class _114FlattenBinaryTree2LinkedList {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode preLevel = new TreeNode(-1);
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.pop();
            preLevel.left = null;
            preLevel.right = node;
            if (node.right != null) {
                queue.push(node.right);
            }

            if (node.left != null) {
                queue.push(node.left);
            }
            preLevel = node;
        }

        System.out.println(root);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(3);
        TreeNode root3 = new TreeNode(4);
        TreeNode root4 = new TreeNode(5);
        TreeNode root5 = new TreeNode(6);
        root.left = root1;
        root.right = root4;
        root1.left = root2;
        root1.right = root3;
        root4.right = root5;

        _114FlattenBinaryTree2LinkedList t = new _114FlattenBinaryTree2LinkedList();
        t.flatten(root);
    }
}
