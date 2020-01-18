package LeetCode;

import java.util.PriorityQueue;

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
        ListNode[] lists = new ListNode[]{head1,head2,head3};

//        System.out.println(mergeKLists(lists).val);
        ListNode res = mergeKListsRecur(lists);
//        System.out.println(merge2Lists(head1,head2).val);
        System.out.println();
    }

    static public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
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
        while (!queue.isEmpty()){
            node.next = new ListNode(queue.poll());
            node = node.next;
        }

        return preHead.next;
    }

    /**
     * 递归解法，归并排序思想
     * 将lists两两合并，直到全部合并成一个
     * @param lists
     * @return
     */
    static public ListNode mergeKListsRecur(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        return doMergeListRecur(lists, 0, lists.length - 1);
    }

    static ListNode doMergeListRecur(ListNode[] lists, int start, int end){
        if (start == end){
            return lists[start];
        }

        int mid = (start + end)/2;
        ListNode left = doMergeListRecur(lists, start, mid);
        ListNode right = doMergeListRecur(lists, mid+1, end);

        return merge2Lists(left, right);
    }

    static ListNode merge2Lists(ListNode node1, ListNode node2){
        if (node1 == null){
            return  node2;
        }

        if (node2 == null){
            return node1;
        }

        ListNode preHead = new ListNode(0);
        ListNode node = preHead;
        while (node1 != null && node2!=null){
            if (node1.val < node2.val){
                node.next = new ListNode(node1.val);
                node1 = node1.next;
                node = node.next;
            } else {
                node.next = new ListNode(node2.val);
                node2 = node2.next;
                node = node.next;
            }
        }

        while (node1 != null){
            node.next = new ListNode(node1.val);
            node1 = node1.next;
            node = node.next;
        }

        while (node2 != null){
            node.next = new ListNode(node2.val);
            node2 = node2.next;
            node = node.next;
        }

        return preHead.next;
    }
}
