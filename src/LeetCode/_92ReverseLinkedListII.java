package LeetCode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-19 下午7:20.
 * Description:
 * <p>
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class _92ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m > n) {
            return head;
        }
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode preStartNode = preHead;
        int index = 0;
        while (index < m - 1) {
            preStartNode = preStartNode.next;
            index++;
        }
        preStartNode.next = reverseList(preStartNode.next, n - m + 1);

        return preHead.next;
    }

    private ListNode reverseList(ListNode head, int count) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = head;
        int step = 0;
        ListNode next = node.next;
        while (step < count - 1) {
            if (next == null) {
                return null;
            }
            ListNode behindNext = next.next;
            next.next = node;
            node = next;
            next = behindNext;
            step++;
        }

        head.next = next;

        return node;
    }

    public static void main(String[] args) {
        _92ReverseLinkedListII t = new _92ReverseLinkedListII();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode listNode = t.reverseBetween(head, 1, 4);
        System.out.println(listNode);
    }
}
