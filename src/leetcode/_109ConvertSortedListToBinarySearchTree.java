package leetcode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-2 下午8:36.
 * Description:
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class _109ConvertSortedListToBinarySearchTree {

    public static void main(String[] args) {
        ListNode head = new ListNode(-10);
        ListNode node1 = new ListNode(-3);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(9);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        _109ConvertSortedListToBinarySearchTree t = new _109ConvertSortedListToBinarySearchTree();
        TreeNode root = t.sortedListToBST(head);
        System.out.println(root.val);
    }


    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        int size = getSize(head);
        this.head = head;
        return constructTree(0, size - 1);
    }

    ListNode head;

    public TreeNode constructTree(int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = (low + high) >> 1;

        TreeNode left = constructTree(low, mid-1);
        TreeNode root = new TreeNode(head.val);
        root.left = left;

        // 左边的树构建完成，开始构建右侧子树，head右移一位
        head = head.next;
        root.right = constructTree(mid + 1, high);
        return root;
    }

    public int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }
}
