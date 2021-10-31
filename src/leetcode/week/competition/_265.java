package leetcode.week.competition;


import java.util.*;

public class _265 {
    public static void main(String[] args) {
        _265 t = new _265();
        int[] n = {2, 1, 3, 5, 2};
//        System.out.println(t.smallestEqual(n));
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(1);
        ListNode l7 = new ListNode(2);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
//        l4.next = l5; l5.next = l6; l6.next = l7;
//        System.out.println(Arrays.toString(t.nodesBetweenCriticalPoints(l1)));
        System.out.println(t.minimumOperations(new int[]{2,8,16}, 0, 1));
    }

    public int minimumOperations(int[] nums, int start, int goal) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> preLevel = new HashSet<>();
        Set<Integer> curLevel = new HashSet<>();
        curLevel.add(start);
        set.add(start);
        int step = 0;
        while (!curLevel.isEmpty() && !preLevel.equals(curLevel)) {
            set.addAll(curLevel);
            step++;
            preLevel = curLevel;
            curLevel = new HashSet<>();
            for (int pre : preLevel) {
                for (int n : nums) {
                    if (n + pre == goal || pre - n == goal || (pre ^ n) == goal) {
                        return step;
                    }

                    if (pre + n <= 1000 && pre + n >= 0 && !set.contains(pre + n)) {
                        curLevel.add(pre + n);
                    }

                    if (pre - n <= 1000 && pre - n >= 0 && !set.contains(pre - n)) {
                        curLevel.add(pre - n);
                    }

                    if ((pre ^ n) <= 1000 && (pre ^ n) >= 0 && !set.contains(pre ^ n)) {
                        curLevel.add(pre ^ n);
                    }
                }
            }

        }

        return -1;
    }

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode pre = head;
        ListNode cur = head.next;
        ArrayList<Integer> extremeList = new ArrayList<>();
        int index = 0;
        while (cur.next != null) {
            if (cur.val > pre.val && cur.val > cur.next.val
                    || (cur.val < pre.val && cur.val < cur.next.val)) {
                extremeList.add(index);
            }
            pre = cur;
            cur = cur.next;
            index++;
        }
        int[] res = new int[]{-1, -1};
        if (extremeList.size() >= 2) {
            int max = extremeList.get(extremeList.size() - 1) - extremeList.get(0);
            int min = max;
            for (int i = 1; i < extremeList.size(); i++) {
                int curDiff = extremeList.get(i) - extremeList.get(i - 1);
                if (curDiff < min) {
                    min = curDiff;
                }
            }
            res[0] = min;
            res[1] = max;
        }
        return res;
    }

    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
