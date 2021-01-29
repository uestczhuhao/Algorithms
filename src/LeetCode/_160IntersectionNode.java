package LeetCode;

/**
 * @author mizhu
 * @date 2021/1/29 16:06
 */
public class _160IntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = 0;
        int lenB = 0;
        ListNode aIndex = headA;
        ListNode bIndex = headB;
        while (aIndex != null) {
            lenA++;
            aIndex = aIndex.next;
        }

        while (bIndex != null) {
            lenB++;
            bIndex = bIndex.next;
        }

        int subLen;
        aIndex = headA;
        bIndex = headB;
        if (lenA > lenB) {
            subLen = lenA - lenB;
            while (subLen > 0) {
                aIndex = aIndex.next;
                subLen--;
            }
        } else {
            subLen = lenB - lenA;
            while (subLen > 0) {
                bIndex = bIndex.next;
                subLen--;
            }
        }

        while (aIndex != null && bIndex != null) {
            if (aIndex == bIndex) {
                return aIndex;
            }
            aIndex = aIndex.next;
            bIndex = bIndex.next;
        }

        return null;
    }

    /**
     * 优化解法，两个指针，分别遍历，则中间的差相互抵消
     * 思考：链表长度为m和n，且m > n，
     * 则走m步后，第一个指针指向第二个链表的head
     * 第二个指针指向第一个链表，且往前走了m-n步，刚好二者并驾齐驱
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode aIndex = headA, bIndex = headB;
        while (aIndex != bIndex) {
            aIndex = aIndex == null ? headB : aIndex.next;
            bIndex = bIndex == null ? headA : bIndex.next;
        }
        return aIndex;
    }

    public static void main(String[] args) {
//        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
//        a.next = b;
        b.next = c;
        d.next = c;
        _160IntersectionNode t = new _160IntersectionNode();
        System.out.println(t.getIntersectionNode1(b, c));
    }
}
