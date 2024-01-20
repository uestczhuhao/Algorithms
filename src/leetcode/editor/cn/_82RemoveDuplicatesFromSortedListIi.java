package leetcode.editor.cn;

public class _82RemoveDuplicatesFromSortedListIi {
    public static void main(String[] args) {
        Solution t = new _82RemoveDuplicatesFromSortedListIi().new Solution();
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
        public ListNode deleteDuplicates(ListNode head) {
            ListNode preHead = new ListNode(-1);
            preHead.next = head;
            ListNode pre = preHead;
            while (pre != null) {
                ListNode cur = pre.next;
                boolean equal = false;
                while (cur != null && cur.next != null && cur.val == cur.next.val) {
                    equal = true;
                    cur = cur.next;
                }
                // 如果equal为true，pre暂时不能后移，防止 22 33的情况发生
                if (equal) {
                    pre.next = cur.next;
                } else {
                    pre = pre.next;
                }
            }


            return preHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
