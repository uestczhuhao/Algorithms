package LeetCode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-16 下午5:46.
 * Description:
 *
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 *
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 *
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 解释：树中蓝色的节点构成了与链表对应的子路径。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 示例 3：
 *
 * 输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：false
 * 解释：二叉树中不存在一一对应链表的路径。
 *  
 *
 * 提示：
 *
 * 二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。
 * 链表包含的节点数目在 1 到 100 之间。
 * 二叉树包含的节点数目在 1 到 2500 之间。
 */
public class _1367LinkedListInBinaryTree {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return head == null;
        }

        return doSubPath(head, root);
    }

    public boolean doSubPath(ListNode listNode, TreeNode treeNode) {
        if (treeNode == null) {
            return listNode == null;
        }

        if (listNode == null) {
            return true;
        }

        boolean equal = listNode.val == treeNode.val;
        boolean left = false;
        boolean right = false;
        if (equal) {
            left = doSubPath(listNode.next, treeNode.left);
            right = doSubPath(listNode.next, treeNode.right);
        } else {
            left = doSubPath(listNode, treeNode.left);
            right = doSubPath(listNode, treeNode.right);
        }

        return equal && (left || right);
    }

    public static void main(String[] args) {
        _1367LinkedListInBinaryTree t = new _1367LinkedListInBinaryTree();
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

        ListNode head = new ListNode(2);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(4);
        head.next = node1;
//        node1.next = node2;

        System.out.println(t.isSubPath(head, root));

    }

}
