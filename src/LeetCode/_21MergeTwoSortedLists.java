package LeetCode;

public class _21MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode index = result;

        while (l1 != null && l2 != null) {
            ListNode next;
            if (l1.val <= l2.val) {
                next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                next = new ListNode(l2.val);
                l2 = l2.next;
            }
            index.next = next;
            index = index.next;
        }
        while (l1 != null) {
            index.next = l1;
            l1 = l1.next;
            index = index.next;
        }

        while (l2 != null) {
            index.next = l2;
            l2 = l2.next;
            index = index.next;
        }

        return result.next;
    }

    public static void main(String[] args) {
        _21MergeTwoSortedLists t = new _21MergeTwoSortedLists();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode res = t.mergeTwoLists(l1, l2);
        print(res);
    }

    private static void print(ListNode res) {
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
