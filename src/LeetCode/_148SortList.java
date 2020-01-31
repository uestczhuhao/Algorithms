package LeetCode;

/**
 * @author mizhu
 * @date 20-1-31 下午4:45
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class _148SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
//        ListNode first = new ListNode(5);
        ListNode first = new ListNode(3);
//        ListNode second = new ListNode(1);
        ListNode second = new ListNode(2);
//        ListNode third = new ListNode(2);
//        ListNode third1 = new ListNode(40);
        ListNode third = new ListNode(1);
        head.next = first;
        first.next = second;
        second.next = third;
//        third.next = third1;
        _148SortList t = new _148SortList();
        t.sortList(head);
//        t.merge2List(head,  second, third1);
        System.out.println();
    }

    /**
     * 采用非递归版的归并排序
     */
    public ListNode sortList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        int len = 0;
        ListNode tempHead = head;
        while (tempHead != null) {
            len++;
            tempHead = tempHead.next;
        }
        for (int step = 1; step < len; step += step) {
            ListNode current = preHead.next;
            for (int i = 0; i < len - step; i += 2 * step) {
                ListNode leftHead = current;
                ListNode rightHead = nextKNode(leftHead, step);
                ListNode rightEnd = nextKNode(rightHead, step);
                // 左闭右开区间，下一次从rightEnd开始
                current = rightEnd;
                //
                if (i == 0) {
                    preHead.next = merge2List(leftHead, rightHead, rightEnd);
                } else {
                    merge2List(leftHead, rightHead, rightEnd);
                }
            }
        }
        return preHead.next;
    }

    private ListNode nextKNode(ListNode head, int k) {
        if (k < 0) {
            return null;
        }

        while (k > 0) {
            if (head == null) {
                break;
            }
            head = head.next;
            k--;
        }
        return head;
    }

    /**
     * 合并两个有序链表，区间为左闭右开
     * TODO : 必须断开后再连起来，否则会发生4->3->2->1 第二次排序后变成 4->3->2 和 1->2，丢元素的情况发生
     */
    private ListNode merge2List(ListNode leftHead, ListNode midNode, ListNode rightEnd) {
        ListNode newListIndex;
        ListNode newHead = new ListNode(-1);
        ListNode rightHead = midNode;
        newListIndex = newHead;
        while (leftHead != midNode && rightHead != rightEnd) {
            if (leftHead.val <= rightHead.val) {
                newListIndex.next = leftHead;
                leftHead = leftHead.next;
            } else {
                newListIndex.next = rightHead;
                rightHead = rightHead.next;
            }
            newListIndex = newListIndex.next;
        }

        while (leftHead != midNode) {
            newListIndex.next = leftHead;
            leftHead = leftHead.next;
            newListIndex = newListIndex.next;
        }

        while (rightHead != rightEnd) {
            newListIndex.next = rightHead;
            rightHead = rightHead.next;
            newListIndex = newListIndex.next;
        }
        newListIndex.next = rightEnd;

        return newHead.next;
    }

}
