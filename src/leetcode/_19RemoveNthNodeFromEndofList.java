package leetcode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 */
public class _19RemoveNthNodeFromEndofList {
    /**
     * 两个指针，一个先走k步，然后两个一起走，
     * 等到第一个走到末尾时，第二个指针指向的就是倒数第k个
     */
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
