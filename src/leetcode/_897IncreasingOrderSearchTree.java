package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-28 下午7:25.
 * Description:
 *
 * 给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
 *
 *  
 *
 * 示例 ：
 *
 * 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
 *
 * 5
 * / \
 * 3    6
 * / \    \
 * 2   4    8
 *  /        / \
 * 1        7   9
 *
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 * 1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 *             \
 *              7
 *               \
 *                8
 *                 \
 * 9
 *  
 *
 * 提示：
 *
 * 给定树中的结点数介于 1 和 100 之间。
 * 每个结点都有一个从 0 到 1000 范围内的唯一整数值。
 */
public class _897IncreasingOrderSearchTree {

    /**
     * 中序遍历，再重新构造树，1ms
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        List<TreeNode> list = new ArrayList<>();
        stack.push(root);
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            list.add(root);
            root = root.right;
        }

        TreeNode node;
        TreeNode next = null;
        for (int i = 0; i < list.size() - 1; i++) {
            node = list.get(i);
            next = list.get(i + 1);
            node.left = null;
            node.right = next;
        }

        next.left = null;
        next.right = null;


        return list.get(0);
    }

    /**
     * 中序遍历树，原地重新构造树
     * @param root
     * @return
     */
    public TreeNode increasingBST1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode dummy = new TreeNode(-1);
        preCurrent = dummy;
        inorder(root);
        return dummy.right;
    }

    private TreeNode preCurrent =null ;

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        // node的right在下一轮更新
        preCurrent.right = node;
        // 到这里时，node的左边都遍历完了，可以直接置为null
        node.left = null;
        preCurrent = node;
        inorder(node.right);
    }
}
