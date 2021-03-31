package leetcode;

/**
 * @author mizhu
 * @date 2020/8/8 16:06
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 */
public class _206ReverseLinkedList {
    /**
     * pre指向node前一个元素（初始为null），
     * behind指向node的下一个元素，令head.next = pre，pre下一次指向node，node指向behing
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preHead = null;
        ListNode node = head;
        ListNode behind;

        while (node != null) {
            behind = node.next;
            node.next = preHead;

            preHead = node;
            node = behind;
        }

        return preHead;
    }

    /**
     * 递归版
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 返回链表新的头部
        ListNode result = reverseList1(head.next);
        head.next.next = head;
        head.next = null;

        return result;
    }

        public static void main(String[] args) {
        _206ReverseLinkedList t = new _206ReverseLinkedList();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode listNode = t.reverseList(head);
        System.out.println(listNode);
    }
}
