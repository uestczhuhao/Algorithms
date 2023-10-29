package leetcode.editor.cn;

public class _203RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution t = new _203RemoveLinkedListElements().new Solution();
        ListNode node1 = new ListNode(7);
        ListNode node2 = new ListNode(7);
        ListNode node3 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        System.out.println(t.removeElements(node1, 7));
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
        public ListNode removeElements(ListNode head, int val) {
            ListNode preHead = new ListNode();
            preHead.next = head;
            ListNode node = preHead;
            while (node != null) {
                while (node.next != null && node.next.val == val) {
                    node.next = node.next.next;
                }
                node = node.next;
            }
            return preHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
