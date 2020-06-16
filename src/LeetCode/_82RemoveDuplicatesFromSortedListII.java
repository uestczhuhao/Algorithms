package LeetCode;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-6-16 下午4:17.
 * Description:
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class _82RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode resultPreHead = preHead;
        while (preHead != null) {
            ListNode node = preHead.next;
            boolean equal = false;
            while (node != null && node.next != null && node.next.val == node.val) {
                equal = true;
                node.next = node.next.next;
            }

            if (equal) {
                preHead.next = node.next;
            } else {
                preHead = preHead.next;
            }
        }

        return resultPreHead.next;
    }

    public static void main(String[] args) {
        _82RemoveDuplicatesFromSortedListII t = new _82RemoveDuplicatesFromSortedListII();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;

        ListNode node = t.deleteDuplicates(head);
        System.out.println(node);

    }
}
