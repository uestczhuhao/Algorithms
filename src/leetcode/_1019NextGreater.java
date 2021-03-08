package leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mizhu
 * @date 20-6-20 下午4:38
 * <p>
 * 给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
 * <p>
 * 每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。
 * <p>
 * 返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
 * <p>
 * 注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1,5]
 * 输出：[5,5,0]
 * 示例 2：
 * <p>
 * 输入：[2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 * 示例 3：
 * <p>
 * 输入：[1,7,5,1,9,2,5,1]
 * 输出：[7,9,9,9,0,5,0,0]
 *  
 * <p>
 * 提示：
 * <p>
 * 对于链表中的每个节点，1 <= node.val <= 10^9
 * 给定列表的长度在 [0, 10000] 范围内
 */
public class _1019NextGreater {
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        List<Integer> data = new ArrayList<>();
        // 存放所有下标，其中下标对应的值应该是单调递减的
        // 因为一旦有递增趋势，则栈顶元素已经找到了其后比它大的第一个值，需要弹出
        Deque<Integer> indexStack = new LinkedList<>();
        // 结果列表，因为是一次遍历，对链表长度未知，只能用list
        List<Integer> largerList = new ArrayList<>();
        int index = 0;
        while (head != null) {
            largerList.add(0);
            data.add(head.val);

            // 判断在index位置的元素是否小于当前元素
            // 若是，则用当前元素的值更新，并将被更新的index弹出
            while (!indexStack.isEmpty() && data.get(indexStack.peek()) < head.val) {
                int targetIndex = indexStack.pop();
                largerList.set(targetIndex, head.val);
            }

            indexStack.push(index);
            index++;
            head = head.next;
        }

        int[] res = new int[largerList.size()];
        index = 0;
        for (Integer larger : largerList) {
            res[index++] = larger;
        }
        return res;

    }

    public int[] nextLargerNodes1(ListNode head) {
        if (head == null) {
            return null;
        }

        int[] ans = new int[10001];
        int[] data = new int[10001];
        Deque<Integer> stack = new LinkedList<>();
        ListNode node = head;
        int index = 0;
        while (node != null) {
            data[index] = node.val;

            while (!stack.isEmpty() && data[stack.peek()] < node.val) {
                Integer curIndex = stack.pop();
                ans[curIndex] = node.val;
            }

            stack.push(index);
            index++;
            node = node.next;
        }

        return Arrays.copyOf(ans, index);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(5);
        head.next = n1;
        n1.next = n2;
        _1019NextGreater t = new _1019NextGreater();
        int[] ints = t.nextLargerNodes1(head);
        System.out.println(Arrays.toString(ints));

    }
}
