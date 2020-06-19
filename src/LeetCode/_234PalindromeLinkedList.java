package LeetCode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-19 下午2:33.
 * Description:
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class _234PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = reverseList(slow.next);

        ListNode half = slow.next;
        while (half != null) {
            if (head.val != half.val) {
                return false;
            }
            head = head.next;
            half = half.next;
        }
        return true;
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
        _234PalindromeLinkedList t = new _234PalindromeLinkedList();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode newHead = t.reverseList(head);
        System.out.println(newHead);
    }
}
