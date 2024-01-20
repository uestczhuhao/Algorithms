package leetcode.editor.cn;

public class _92ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution t = new _92ReverseLinkedListIi().new Solution();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode listNode = t.reverseBetween(head, 2, 4);
        System.out.println(listNode);
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
        /**
         * 头插法，每次把p后面的元素删除，插入到pre节点的后面
         * https://leetcode.cn/problems/reverse-linked-list-ii/solutions/138910/java-shuang-zhi-zhen-tou-cha-fa-by-mu-yi-cheng-zho/?envType=study-plan-v2&envId=top-interview-150
         */
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode preHead = new ListNode(-1);
            preHead.next = head;
            ListNode pre = preHead;
            int step = 0;
            while (step < left - 1) {
                pre = pre.next;
                step++;
            }

            step = 1;
            ListNode node = pre.next;
            while (step < right - left + 1) {
                ListNode removed = node.next;
                node.next = removed.next;

                removed.next = pre.next;
                pre.next = removed;
                step ++;
            }
            return preHead.next;
        }

        public ListNode reverseBetween1(ListNode head, int left, int right) {
            ListNode preHead = new ListNode(-1);
            preHead.next = head;
            ListNode pre = preHead;
            int step = 0;
            while (step < left - 1) {
                pre = pre.next;
                step++;
            }

            pre.next = reverse(pre.next, right - left + 1);

            return preHead.next;
        }

        /**
         * 头插法，举例：2(node)->3(next)->4(behind)三个节点，一次转化后
         * 转化为3->2 4，此时3为新的node，4为新的next，继续下一次循环
         * 注意最后把2 3 4反转为4 3 2后，把原先4后面的5挂到2后面
         */
        private ListNode reverse(ListNode head, int step) {
            ListNode node = head;
            int count = 0;
            ListNode next = node.next, behind;
            while (count < step - 1) {
                behind = next.next;
                next.next = node;

                node = next;
                next = behind;
                count ++;
            }
            head.next = next;
            return node;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
