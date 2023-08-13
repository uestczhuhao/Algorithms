package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class _358 {
    public static void main(String[] args) {
        _358 t = new _358();
        int[] nums = {1,2,3,4};
//        System.out.println(t.maxSum(nums));
        ListNode tail = new ListNode(9);
        ListNode node1 = new ListNode(9, tail);
        ListNode node2 = new ListNode(9, node1);
        ListNode res = t.doubleIt(node2);
        System.out.println(res);
    }

    /**
     * 思路：题目要求 对一个下标i，当j>=x+i时，选所有满足条件的j，使得 abs(nums[i] - nums[j]) 的值最小
     * 1. 不妨对j遍历，当j往后移动时，只需简单的把j-x加入待选集合即可
     * 2. 待选集合放入TreeSet，当指针在j时，只需要选取low和high的值即可，其中low和high代表比nums[j]小和比它大的最接近的值
     * 注意：当 low或high 有一个不存在时，取另一个即可（不可能都不存在），如：待选集合有10,100,1000，nums[j]=1，则取10
     */
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int len = nums.size(), ans = Integer.MAX_VALUE;
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = x; i<len; i++) {
            set.add(nums.get(i - x));
            int cur = nums.get(i);
            // 在log(n)时间复杂度返回大于等于cur的值，没有则返回null
            Integer cel = set.ceiling(cur);
            if (cel != null) {
                ans = Math.min(ans, cel - cur);
            }

            Integer flr = set.floor(cur);
            if (flr != null) {
                ans = Math.min(ans, cur - flr);
            }

        }

        return ans;
    }

    public ListNode doubleIt(ListNode head) {
        head = reverseList(head);
        int carry = 0;
        ListNode preHead = new ListNode();
        ListNode tail = new ListNode();
        preHead.next = head;
        while (head != null) {
            if (head.next == null) {
                tail = head;
            }
            int cur = head.val * 2 + carry;
            head.val = cur % 10;
            carry = cur / 10;
            head = head.next;
        }
        if (carry != 0) {
            tail.next = new ListNode(carry);
        }
        return reverseList(preHead.next);
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preHead = null;
        ListNode node = head;
        ListNode behind;

        while (node != null) {
            behind = node.next;
            node.next = preHead;

            preHead = node;
            node = behind;
        }

        return preHead;
    }

    public int maxSum(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int n : nums) {
            int origin = n;
            int max = n % 10;
            n /= 10;
            while (n > 0) {
                max = Math.max(max, n % 10);
                n /= 10;
            }
            map.putIfAbsent(max, new ArrayList<>());
            map.get(max).add(origin);
        }
        int ans = -1;
        for (Map.Entry<Integer, List<Integer>> m : map.entrySet()) {
            if (m.getValue().size() >= 2) {
                PriorityQueue<Integer> a = new PriorityQueue<>(Comparator.reverseOrder());
                a.addAll(m.getValue());
                ans = Math.max(ans, a.poll() + a.poll());
            }
        }
        return ans;
    }
}
