package LeetCode;

public class _19RemoveNthNodeFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode fast = pre, low = pre;
        for (int i=0;i<=n && fast != null;i++){
            fast = fast.next;
        }

        while (fast != null){
            fast = fast.next;
            low = low.next;
        }

        low.next = low.next.next;

        return pre.next;
    }
    private static void print(ListNode res){
        while (res != null){
            System.out.print(res.val+ " ");
            res = res.next;
        }
    }

    public static void main(String[] args) {
        _19RemoveNthNodeFromEndofList t = new _19RemoveNthNodeFromEndofList();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        print(l1);
        System.out.println();
        ListNode res = t.removeNthFromEnd(l1,5);
        print(res);
    }
}
