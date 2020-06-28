package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-24 下午5:09.
 * Description:
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 */
public class _226InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(cur);
        while (!stack.isEmpty()){
            cur = stack.pop();
            TreeNode tmp = cur.right;
            cur.right = cur.left;
            cur.left = tmp;

            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        return root;
    }
}
