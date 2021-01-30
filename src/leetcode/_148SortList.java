package leetcode;

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
        ListNode first = new ListNode(2);
//        ListNode second = new ListNode(1);
        ListNode second = new ListNode(1);
//        ListNode third = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode third1 = new ListNode(40);
        ListNode third2 = new ListNode(5);
        head.next = first;
        first.next = second;
        second.next = third;
        third.next = third1;
        third1.next = third2;
        _148SortList t = new _148SortList();
//        ListNode newHead = t.sortList(head);
        ListNode newHead = t.sortList1(head);
//        t.merge2List(head,  second, third1);
        print(newHead);
//        System.out.println(newHead);
    }

    private static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public ListNode sortList1(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }


        ListNode rightHead = cutHalf(head);
        ListNode leftHead = sortList1(head);
        rightHead = sortList1(rightHead);

        return merge2List(leftHead, rightHead);
    }

    private ListNode cutHalf(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode result = slow.next;
        slow.next = null;
        return result;
    }
    /**
     * 采用非递归版的归并排序
     * 1. 先断开，再连上
     * 2. 每一轮进行前，要记录本轮之后的节点，
     * 如：4 -> 3 -> 2 -> 1 -> 5，在merge 2->1 时，要记录5，merge完成后把5 接到后面
     * 3. 记录上一轮的最终节点lastRoundLast，下一轮merge完成后，将本轮头节点thisRoundHead接到lastRoundLast后面
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
            ListNode lastRoundLast = null;
            for (int i = 0; i < len - step && current != null; i += 2 * step) {
                ListNode leftHead = current;
                ListNode leftEnd = nextKNode(leftHead, step - 1);
                // leftEnd为null，这一轮右边无节点，直接返回
                if (leftEnd == null) {
                    break;
                }
                ListNode rightHead = leftEnd.next;
                // 左边断开
                leftEnd.next = null;
                ListNode rightEnd = nextKNode(rightHead, step - 1);
                // current指向下一轮的head节点
                current = rightEnd == null ? null : rightEnd.next;
                // 右边断开
                if (rightEnd != null) {
                    rightEnd.next = null;
                }

                ListNode thisRoundHead = merge2List(leftHead, rightHead);
                if (i == 0) {
                    preHead.next = thisRoundHead;
                } else {
                    lastRoundLast.next = thisRoundHead;
                }
                ListNode thisRoundLast = thisRoundHead;
                while (thisRoundLast.next != null) {
                    thisRoundLast = thisRoundLast.next;
                }
                // 本轮的末节点和下一轮的头结点联合起来
                thisRoundLast.next = current;
                // 记录本轮的末节点，用于连接下一轮merge后的新头节点
                lastRoundLast = thisRoundLast;
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
     * 合并两个有序链表，必须断开后再连起来，否则会发生4->3->2->1 第二次排序后变成 4->3->2 和 1->2，丢元素的情况发生
     */
    private ListNode merge2List(ListNode leftHead, ListNode rightHead) {
        ListNode newListIndex;
        ListNode newHead = new ListNode(-1);
        newListIndex = newHead;
        while (leftHead != null && rightHead != null) {
            if (leftHead.val <= rightHead.val) {
                newListIndex.next = leftHead;
                leftHead = leftHead.next;
            } else {
                newListIndex.next = rightHead;
                rightHead = rightHead.next;
            }
            newListIndex = newListIndex.next;
        }

        while (leftHead != null) {
            newListIndex.next = leftHead;
            leftHead = leftHead.next;
            newListIndex = newListIndex.next;
        }

        while (rightHead != null) {
            newListIndex.next = rightHead;
            rightHead = rightHead.next;
            newListIndex = newListIndex.next;
        }
        return newHead.next;
    }

}
