package leetcode.editor.cn;

//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL, k = 2
//输出: 4->5->1->2->3->NULL
//解释:
//向右旋转 1 步: 5->1->2->3->4->NULL
//向右旋转 2 步: 4->5->1->2->3->NULL
// 
//
// 示例 2: 
//
// 输入: 0->1->2->NULL, k = 4
//输出: 2->0->1->NULL
//解释:
//向右旋转 1 步: 2->0->1->NULL
//向右旋转 2 步: 1->2->0->NULL
//向右旋转 3 步: 0->1->2->NULL
//向右旋转 4 步: 2->0->1->NULL 
// Related Topics 链表 双指针 
// 👍 418 👎 0


import java.util.List;

public class _61RotateList {
    public static void main(String[] args) {
        Solution t = new _61RotateList().new Solution();
    }

    /**

     */
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
         * 思路：先找到链表长度n，再找到倒数第k % n 个元素，把找到的元素当成新的根节点
         */
        public ListNode rotateRight(ListNode head, int k) {
            if (k <= 0 || head == null) {
                return head;
            }

            int len = 0;
            ListNode target = null, preTarget = null, tail = head, index = head;
            while (index != null) {
                tail = index;
                len++;
                index = index.next;
            }

            k = k % len;
            if (k == 0) {
                return head;
            } else {
                preTarget = head;
                for (int i = 0; i < len - k - 1; i++) {
                    preTarget = preTarget.next;
                }
                target = preTarget.next;
                preTarget.next = null;
                tail.next = head;
                return target;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

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