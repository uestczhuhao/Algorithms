package leetcode.editor.cn;

public class _86PartitionList {
    public static void main(String[] args) {
        Solution t = new _86PartitionList().new Solution();
        ListNode node6 = new ListNode(2);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(2, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(4, node3);
        ListNode node1 = new ListNode(1, node2);

        System.out.println(t.partition(node1, 3));
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
        public ListNode partition(ListNode head, int x) {
            ListNode low = new ListNode(-1);
            ListNode high = new ListNode(-1);
            ListNode larger = high, smaller = low;
            while (head != null) {
                if (head.val < x) {
                    low.next = head;
                    low = low.next;
                } else {
                    high.next = head;
                    high = high.next;
                }
                head = head.next;
            }
            high.next = null;
            low.next = larger.next;

            return smaller.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
