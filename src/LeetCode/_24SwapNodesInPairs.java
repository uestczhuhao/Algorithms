package LeetCode;

public class _24SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);
        head1.next.next.next = new ListNode(6);
        head1.next.next.next.next = new ListNode(2);
        head1.next.next.next.next.next = new ListNode(3);

        ListNode res = swapPairs(head1);
        System.out.println();
    }

    /**
     * 原思路，四个指针，preNode,curLeft,curRight,postNode
     * 优化后无需postNode也可以（况且引入postNode还需要判空）
     * @param head
     * @return
     */
    static public ListNode swapPairs(ListNode head){
        if (head == null || head.next == null){
            return head;
        }

        ListNode res = head.next;
        ListNode preNode = new ListNode(0);
        preNode.next = head;


        while (preNode.next != null && preNode.next.next !=null){
            ListNode curLeft = preNode.next;
            ListNode curRight = preNode.next.next;

            preNode.next = curRight;
            curLeft.next = curRight.next;
            curRight.next = curLeft;
            preNode = curLeft;
        }
        return  res;
    }
}
