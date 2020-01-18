package LeetCode;

import java.util.List;
import java.util.Stack;

public class _25ReverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(6);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next = new ListNode(8);

        ListNode newHead = reverseKGroup(head,2);
        System.out.println(newHead);
    }

    static ListNode reverseKGroup(ListNode head, int k){
        if (k <= 0 || head == null){
            return null;
        }

        if (k == 1){
            return head;
        }
        ListNode newHead = reverseKNodes(head, k);
        ListNode node = head;
        while (node.next != null){
            ListNode next = node.next;
            ListNode rever = reverseKNodes(next, k);
            if (next == rever){
                break;
            }
            node.next = rever;
            node = next;
        }

        return newHead;
    }

    /**
     * 翻转k个节点，注意保存next
     * @return
     */
    private static Stack<ListNode> stack = new Stack<>();
    static private ListNode reverseKNodes(ListNode head, int k){
        ListNode next;
        ListNode node = head;
        for (int i=0;i<k;i++){
            stack.push(node);
            node = node.next;
            // 防止：最后一次还有两个节点，但k=3的情况
            // 当 i = k-1，则刚好最后还剩3个节点
            if (node == null && i != k-1){
                return head;
            }
        }
        next = node;

        if (stack.isEmpty()){
            return null;
        }
        ListNode newHead = stack.pop();
        node = newHead;
        while (!stack.isEmpty()){
            node.next = stack.pop();
            node = node.next;
        }
        node.next = next;
        return newHead;
    }
}
