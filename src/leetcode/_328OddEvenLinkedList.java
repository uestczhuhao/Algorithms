package leetcode;

import static leetcode._23MergeKSortedLists.print;

/**
 * @author mizhu
 * @date 20-5-31 下午10:58
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class _328OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        print(head);
        _328OddEvenLinkedList t = new _328OddEvenLinkedList();
        print(t.oddEvenList1(head));
    }

    /**
     * 写开，更清晰
     */
    public ListNode oddEvenList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode node = head;
        ListNode preNextNode = node.next;
        ListNode afterPreNextNode;
        while (preNextNode != null) {
            afterPreNextNode = preNextNode.next == null ? null : preNextNode.next.next;
            ListNode delNode = deleteAfter(preNextNode);
            node = addAfter(node, delNode);

            preNextNode = afterPreNextNode;
        }

        return head;
    }

    /**
     * 思路：跳跃前进，维护两个链表，奇数列表odd和偶数列表even（注意维护偶数列表头）
     * 遍历过程中两个列表分别变长，最后把偶数链表的头链接进奇数列表的尾
     * @param head
     * @return
     */
    public ListNode oddEvenList1(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = odd.next;
        }
        odd.next = evenHead;

        return head;
    }

    /**
     * 从preNode后面删除节点，返回删除的节点
     */
    public ListNode deleteAfter(ListNode preNode) {
        if (preNode == null || preNode.next == null) {
            return null;
        }

        ListNode node = preNode.next;
        preNode.next = node.next;
        node.next = null;
        return node;
    }

    /**
     * 在target之后插入一个节点，返回插入的节点
     */
    public ListNode addAfter(ListNode node, ListNode candidate) {
        if (candidate == null || node == null) {
            return null;
        }

        candidate.next = node.next;
        node.next = candidate;

        return candidate;
    }
}
