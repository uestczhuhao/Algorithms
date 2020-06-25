package LeetCode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-24 上午11:11.
 * Description:
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */
public class _94BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return  new ArrayList<>();
        }

        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> inorder = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            // 先向左到底
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 第一个弹出的，一定的最左的或左边的已经全弹出的节点
            root = stack.pop();
            inorder.add(root.val);
            root = root.right;
        }

        return inorder;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode fLeft = new TreeNode(2);
        TreeNode fRight = new TreeNode(3);
        TreeNode sLLeft = new TreeNode(4);
        TreeNode sLRight = new TreeNode(5);
        TreeNode sRLeft = new TreeNode(6);
        TreeNode sRRight = new TreeNode(7);
        root.left = fLeft;
        root.right = fRight;
        fLeft.left = sLLeft;
        fLeft.right = sLRight;
        fRight.left = sRLeft;
        fRight.right = sRRight;

        _94BinaryTreeInorderTraversal t = new _94BinaryTreeInorderTraversal();
        System.out.println(t.inorderTraversal(root));
    }
}
