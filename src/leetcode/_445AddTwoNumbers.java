package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 20-6-21 上午9:52
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class _445AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        Deque<Integer> srcStack = new LinkedList<>();
        while (l1 != null) {
            srcStack.push(l1.val);
            l1 = l1.next;
        }

        Deque<Integer> tgtStack = new LinkedList<>();
        while (l2 != null) {
            tgtStack.push(l2.val);
            l2 = l2.next;
        }
        ListNode tail = null, head = null;
        int carry = 0;
        // 将链表逆序的存入栈中，再依次弹出做加法，注意进位
        while (!srcStack.isEmpty() || !tgtStack.isEmpty()) {
            int sum;
            if (srcStack.isEmpty()) {
                sum = tgtStack.pop() + carry;
            } else if (tgtStack.isEmpty()) {
                sum = srcStack.pop() + carry;
            } else {
                sum = srcStack.pop() + tgtStack.pop() + carry;
            }
            head = new ListNode(sum % 10);
            head.next = tail;
            tail = head;
            carry = sum / 10;
        }

        // 如果加到最后，进位不为0，则将其放入结果栈中
        if (carry > 0) {
            head = new ListNode(carry);
            head.next = tail;
        }

        return head;
    }

    public static void main(String[] args) {
        _445AddTwoNumbers t = new _445AddTwoNumbers();
        ListNode head = new ListNode(7);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        ListNode head1 = new ListNode(9);
        ListNode node11 = new ListNode(6);
        ListNode node21 = new ListNode(4);
        ListNode node31 = new ListNode(5);
        head1.next = node11;
        node11.next = node21;
        node21.next = node31;

        System.out.println(t.addTwoNumbers(head, head1));
    }
}
