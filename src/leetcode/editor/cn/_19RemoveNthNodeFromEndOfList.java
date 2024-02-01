package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class _19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution t = new _19RemoveNthNodeFromEndOfList().new Solution();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        System.out.println(t.removeNthFromEnd(l1, 2));

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode removeNthFromEnd1(ListNode head, int n) {
            int traverse = traverse(head, n);
            if (traverse == n) {
                return head.next;
            }
            return head;
        }

        private int traverse(ListNode node, int n) {
            if (node == null) {
                return 0;
            }
            int num = traverse(node.next, n);
            if (num == n) {
                node.next = node.next.next;
            }
            return num + 1;
        }

        /**
         * 利用HashMap记录结点位置，遍历完一遍后直接将倒数n-1的next指向倒数n+1，真·一次遍历
         * 优于链表（也可以用ArrayList，直接add进去，最后取倒数第N个）
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            Map<Integer, ListNode> map = new HashMap<>();
            ListNode pre = new ListNode(0, head);
            int count = 0;
            ListNode current = pre;
            map.put(count, current);
            while (current.next != null) {
                count ++;
                current = current.next;
                map.put(count, current);
            }
            map.get(count - n).next = map.get(count - n + 2);
            return pre.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
