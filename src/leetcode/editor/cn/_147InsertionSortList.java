package leetcode.editor.cn;

/**
 * <p>给定单个链表的头<meta charset="UTF-8" />&nbsp;<code>head</code>&nbsp;，使用 <strong>插入排序</strong> 对链表进行排序，并返回&nbsp;<em>排序后链表的头</em>&nbsp;。</p>
 *
 * <p><strong>插入排序</strong>&nbsp;算法的步骤:</p>
 *
 * <ol>
 * <li>插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。</li>
 * <li>每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。</li>
 * <li>重复直到所有输入数据插入完为止。</li>
 * </ol>
 *
 * <p>下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。</p>
 *
 * <p>对链表进行插入排序。</p>
 *
 * <p><img alt="" src="https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif" /></p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/04/sort1linked-list.jpg" /></p>
 *
 * <pre>
 * <strong>输入:</strong> head = [4,2,1,3]
 * <strong>输出:</strong> [1,2,3,4]</pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/04/sort2linked-list.jpg" /></p>
 *
 * <pre>
 * <strong>输入:</strong> head = [-1,5,3,4,0]
 * <strong>输出:</strong> [-1,0,3,4,5]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <p><meta charset="UTF-8" /></p>
 *
 * <ul>
 * <li>列表中的节点数在&nbsp;<code>[1, 5000]</code>范围内</li>
 * <li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li><li>排序</li></div></div><br><div><li>👍 516</li><li>👎 0</li></div>
 */

public class _147InsertionSortList {
    public static void main(String[] args) {
        Solution t = new _147InsertionSortList().new Solution();
        ListNode head = new ListNode(-1);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(0);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(t.insertionSortList(head));
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
        public ListNode insertionSortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(5010);
            dummy.next = head;
            ListNode cur = head.next, lastSorted = head;
            while (lastSorted.next != null) {
                if (lastSorted.val <= cur.val) {
                    lastSorted = lastSorted.next;
                } else {
                    ListNode prev = dummy;
                    // 找到pre.next比cur大的地方，即为cur插入的地方
                    // 此处不会越界，原因是这里满足lastSorted.val > cur.val，因此一定能找到一个位置插入cur
                    while (prev.next.val <= cur.val) {
                        prev = prev.next;
                    }
                    // 插入cur的同时，原链表删除cur
                    lastSorted.next = cur.next;
                    cur.next = prev.next;
                    prev.next = cur;
                }
                cur = lastSorted.next;
            }
            return dummy.next;
        }

        public ListNode insertionSortList1(ListNode head) {
            ListNode dummy = new ListNode(5010);
            ListNode cur = dummy;
            dummy.next = head;
            while (cur != null && cur.next != null) {
                ListNode index = cur, preMin = cur;
                while (index.next != null) {
                    if (index.next.val < preMin.next.val) {
                        preMin = index;
                    }
                    index = index.next;
                }
                if (preMin != cur) {
                    insertNextNode(cur, delNextNode(preMin));
                }
                cur = cur.next;
            }
            return dummy.next;
        }

        private ListNode delNextNode(ListNode node) {
            ListNode deletedNode = node.next;
            node.next = deletedNode.next;
            deletedNode.next = null;
            return deletedNode;
        }

        private void insertNextNode(ListNode src, ListNode tgt) {
            ListNode next = src.next;
            src.next = tgt;
            tgt.next = next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
