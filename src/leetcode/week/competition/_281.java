package leetcode.week.competition;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class _281 {
    public static void main(String[] args) {
        _281 t = new _281();
//        System.out.println(t.countEven(4));
//        System.out.println(t.countEven(30));
//        System.out.println(t.countEven1(1000));
//        System.out.println(t.countEven(1000));
        ListNode node8 = new ListNode(0);
        ListNode node7 = new ListNode(2, node8);
        ListNode node6 = new ListNode(5, node7);
        ListNode node5 = new ListNode(4, node6);
        ListNode node4 = new ListNode(0, node5);
        ListNode node3 = new ListNode(0);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(0, node2);
//        System.out.println(t.mergeNodes(node1));
//        System.out.println(t.repeatLimitedString("aababab", 2));
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(t.coutPairs(nums, 2));
    }

    /**
     * 1.求出每一个num与k的最大公约数，按照最大公约数分类，其中value是该公约数出现的次数
     * 2.对公约数k1和k2，若k1 * k2 能被k整除，则k1所属的每一个都能与k2所属的每一个组成满足条件的下标对
     */
    public long coutPairs(int[] nums, int k) {
        Map<Integer, Integer> numGcdMap = new HashMap<>();
        long ans = 0;
        for (int num : nums) {
            int gcd = gcd(num, k);
            numGcdMap.put(gcd, numGcdMap.getOrDefault(gcd, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> k1 : numGcdMap.entrySet()) {
            for (Map.Entry<Integer, Integer> k2 : numGcdMap.entrySet()) {
                if ((long) k1.getKey() * k2.getKey() % k == 0) {
                    if (Objects.equals(k1.getKey(), k2.getKey())) {
                        ans += (long) k1.getValue() * (k1.getValue() - 1) / 2;
                    } else if (k1.getKey() < k2.getKey()) {
                        ans += (long) k1.getValue() * k2.getValue();
                    }
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        int[] chNum = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chNum[s.charAt(i) - 'a']++;
        }
        int cur = 25;
        for (; cur >= 0; cur--) {
            if (chNum[cur] > 0) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        int pre = cur;
        while (cur >= 0 && pre >= 0) {
            if (pre < cur) {
                sb.append((char) ('a' + pre));
                chNum[pre]--;
                pre = cur;
            }
            int min = Math.min(repeatLimit, chNum[cur]);
            for (int i = 0; i < min; i++) {
                sb.append((char) ('a' + cur));
            }
            chNum[cur] -= min;
            if (chNum[cur] > 0) {
                for (pre = cur - 1; pre >= 0; pre--) {
                    if (chNum[pre] > 0) {
                        break;
                    }
                }
            } else {
                for (; cur >= 0; cur--) {
                    if (chNum[cur] > 0) {
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    public ListNode mergeNodes(ListNode head) {
        ListNode preHead = new ListNode();
        preHead.next = head.next;
        ListNode cur = head.next;
        while (cur != null && cur.next != null) {
            ListNode index = cur.next;
            while (index != null && index.val != 0) {
                cur.val += index.val;
                index = index.next;
            }
            if (index == null) {
                cur.next = null;
                break;
            } else {
                cur.next = index.next;
                cur = cur.next;
            }
        }
        return preHead.next;
    }

    public int countEven1(int num) {
        if (num <= 0) {
            return 0;
        }

        int tenth = num / 10;
        int ans = 0;
        if (tenth > 0) {
            ans += tenth * 5 - 1;
        }
        int i = Math.max(tenth * 10, 1);
        for (; i <= num; i++) {
            if ((i / 1000 + (i / 100 % 10) + (i / 10 % 10) + i % 10) % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }

    public int countEven(int num) {
        if (num <= 0) {
            return 0;
        }

        int ans = 0;
        for (int i = 1; i <= num; i++) {
            if ((i / 1000 + (i / 100 % 10) + (i / 10 % 10) + i % 10) % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }
}
