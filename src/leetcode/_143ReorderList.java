package leetcode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-19 下午3:37.
 * Description:
 *
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 */
public class _143ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 链表的后一半反转
        slow.next = reverseList(slow.next);
        ListNode reverse = slow.next;
        slow.next = null;

        ListNode preNext;
        ListNode revNext;
        while (head != null && reverse !=null) {
            preNext = head.next;
            revNext = reverse.next;

            head.next = reverse;
            reverse.next = preNext;

            head = preNext;
            reverse = revNext;
        }

    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = head;
        ListNode next = node.next;
        while (next != null) {
            ListNode behindNext = next.next;
            next.next = node;
            node = next;
            next = behindNext;
        }

        head.next = null;

        return node;

    }

    public static void main(String[] args) {
        _143ReorderList t = new _143ReorderList();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        t.reorderList(head);
        System.out.println(head);
    }
}
