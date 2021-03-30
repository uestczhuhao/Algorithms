package leetcode;

public class _24SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);
        head1.next.next.next = new ListNode(6);
        head1.next.next.next.next = new ListNode(2);
        head1.next.next.next.next.next = new ListNode(3);

        ListNode res = swapPairs1(head1);
        System.out.println();
    }

    /**
     * 原思路，四个指针，preNode,curLeft,curRight,postNode
     * 优化后无需postNode也可以（况且引入postNode还需要判空）
     *
     * @param head
     * @return
     */
    static public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode res = head.next;
        ListNode preNode = new ListNode(0);
        preNode.next = head;


        while (preNode.next != null && preNode.next.next != null) {
            ListNode curLeft = preNode.next;
            ListNode curRight = preNode.next.next;

            preNode.next = curRight;
            curLeft.next = curRight.next;
            curRight.next = curLeft;
            preNode = curLeft;
        }
        return res;
    }

    /**
     * head表示头节点，newHead表示head的下一个，交换后的新节点，
     * 接下来就是递归交换newHead.next的节点，交换的结果链到head.next上，再把head链到newHead后面，返回newHead即可
     * 当head为空或只有一个时递归退出
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
