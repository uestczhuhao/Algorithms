package LeetCode;

import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class _23MergeKSortedLists {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(6);
        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(7);
        ListNode[] lists = new ListNode[]{head1, head2, head3};

//        System.out.println(mergeKLists(lists).val);
        ListNode res = mergeKList(lists);
        print(res);
//        System.out.println(merge2Lists(head1,head2).val);
        System.out.println();
    }

    static public void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    /**
     * 非递归版，借助优先队列，一个个放进，再一个个取出
     */
    static public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode preHead = new ListNode(0);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (ListNode node : lists) {
            while (node != null) {
                queue.offer(node.val);
                node = node.next;
            }
        }
        ListNode node = preHead;
        while (!queue.isEmpty()) {
            node.next = new ListNode(queue.poll());
            node = node.next;
        }

        return preHead.next;
    }

    /**
     * 递归解法，归并排序思想
     * 将lists两两合并，直到全部合并成一个
     */
    static public ListNode mergeKListsRecur(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return doMergeListRecur(lists, 0, lists.length - 1);
    }

    /**
     * 非递归版的，时间复杂度和递归版的相同
     */
    static public ListNode mergeKList(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int length = lists.length;
        for (int step = 1; step < length; step += step) {
            for (int i = 0; i < length - step; i += 2 * step) {
                lists[i] = merge2Lists(lists[i], lists[i + step]);
            }
        }
        return lists[0];
    }

    static ListNode doMergeListRecur(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = (start + end) / 2;
        ListNode left = doMergeListRecur(lists, start, mid);
        ListNode right = doMergeListRecur(lists, mid + 1, end);

        return merge2Lists(left, right);
    }

    static ListNode merge2Lists(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        ListNode preHead = new ListNode(0);
        ListNode node = preHead;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                node.next = new ListNode(node1.val);
                node1 = node1.next;
                node = node.next;
            } else {
                node.next = new ListNode(node2.val);
                node2 = node2.next;
                node = node.next;
            }
        }

        while (node1 != null) {
            node.next = new ListNode(node1.val);
            node1 = node1.next;
            node = node.next;
        }

        while (node2 != null) {
            node.next = new ListNode(node2.val);
            node2 = node2.next;
            node = node.next;
        }

        return preHead.next;
    }
}
