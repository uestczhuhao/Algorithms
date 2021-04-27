package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mizhu
 * @date 20-6-11 下午10:32
 * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 * <p>
 * 删除完毕后，请你返回最终结果链表的头节点。
 * <p>
 *  
 * <p>
 * 你可以返回任何满足题目要求的答案。
 * <p>
 * （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,-3,3,1]
 * 输出：[3,1]
 * 提示：答案 [1,2,1] 也是正确的。
 * 示例 2：
 * <p>
 * 输入：head = [1,2,3,-3,4]
 * 输出：[1,2,4]
 * 示例 3：
 * <p>
 * 输入：head = [1,2,3,-3,-2]
 * 输出：[1]
 *  
 * <p>
 * 提示：
 * <p>
 * 给你的链表中可能有 1 到 1000 个节点。
 * 对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000.
 */
public class _1171RemoveZeroSumConsecutiveNodes {
    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) {
            return null;
        }

        Map<Integer, ListNode> sumMap = new HashMap<>();
        // 借助初始值为0的dummy节点的好处：可以安全的删除head节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int sum = 0;
        // 第一次遍历
        // 记录下从head到node的和sum，map的value为当前和的最后一个节点（后面的覆盖前面）
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            sumMap.put(sum, node);
        }

        sum = 0;
        // 第二遍遍历 若当前节点处sum在下一处出现了则表明两结点之间所有节点和为0 直接删除区间所有节点
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            if (sumMap.containsKey(sum)) {
                node.next = sumMap.get(sum).next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        _1171RemoveZeroSumConsecutiveNodes t = new _1171RemoveZeroSumConsecutiveNodes();
        ListNode head = new ListNode(3);
        ListNode first = new ListNode(-3);
        ListNode second = new ListNode(1);
        head.next = first;
        first.next = second;
        head = t.removeZeroSumSublists(head);
        System.out.println(head);
    }
}
