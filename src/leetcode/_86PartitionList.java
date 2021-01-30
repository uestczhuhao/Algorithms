package leetcode;

/**
 * @author mizhu
 * @date 20-6-11 下午11:31
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 */
public class _86PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode low = new ListNode(Integer.MIN_VALUE);
        ListNode high = new ListNode(Integer.MAX_VALUE);
        ListNode preHead = low;
        ListNode preLarger = high;
        while (head!=null) {
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
        low.next = preLarger.next;

        return preHead.next;
    }

    public static void main(String[] args) {
        _86PartitionList t = new _86PartitionList();
        ListNode head = new ListNode(1);
        ListNode first = new ListNode(4);
        ListNode second = new ListNode(3);
        ListNode second1 = new ListNode(2);
        ListNode second2 = new ListNode(5);
        ListNode second3 = new ListNode(2);
        head.next = first;
        first.next = second;
        second.next = second1;
        second1.next = second2;
        second2.next = second3;
        head = t.partition(head, 3);
        System.out.println(head);
    }
}
