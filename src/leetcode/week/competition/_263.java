package leetcode.week.competition;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class _263 {
    public static void main(String[] args) {
        _263  t = new _263();
        String s = "sunset is at 7 7 pm overnight lows will be in the low 50 and 60 s";
//        System.out.println(t.areNumbersAscending(s));
        int[] nums = {3,2,1,5};
        System.out.println(t.countMaxOrSubsets(nums));
    }

    Map<Integer, Integer> resFreq = new HashMap<>();
    public int countMaxOrSubsets(int[] nums) {
        dfs(0, new LinkedList<>(), nums);
        int fre = 0;
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : resFreq.entrySet()) {
            if (entry.getKey() > max) {
                max = entry.getKey();
                fre = entry.getValue();
            }
        }

        return fre;
    }

    private void dfs(int start, LinkedList<Integer> current, int[] nums) {
        System.out.println(current);
        int curOr = countOrRes(current);
        resFreq.put(curOr, resFreq.getOrDefault(curOr, 0) + 1);

        for (int i = start; i<nums.length; i++) {
            current.add(nums[i]);
            dfs(i + 1, current, nums);
            current.removeLast();
        }
    }

    private int countOrRes(List<Integer> current) {
        int ans = 0;
        for (int cur: current) {
            ans |= cur;
        }
        return ans;
    }

    class Bank {
        int userNum;
        long[] balance;
        public Bank(long[] balance) {
            userNum = balance.length;
            this.balance = balance;
        }

        public boolean transfer(int account1, int account2, long money) {
            if (!validAccount(account1) || !validAccount(account2)) {
                return false;
            }

            if (balance[account1 - 1] < money) {
                return false;
            }
            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;
            return true;
        }

        public boolean deposit(int account, long money) {
            if (!validAccount(account)) {
                return false;
            }
            balance[account - 1] += money;
            return true;
        }

        public boolean withdraw(int account, long money) {
            if (!validAccount(account) || balance[account - 1] < money) {
                return false;
            }
            balance[account - 1] -= money;
            return true;
        }

        private boolean validAccount(int account) {
            return account >= 1 && account <= userNum;
        }
    }

    public boolean areNumbersAscending(String s) {
        int pre = 0;
        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index)>='0' && s.charAt(index) <='9') {
                int cur = 0;
                while (index < s.length() && s.charAt(index) != ' ') {
                    cur = cur * 10 + s.charAt(index) - '0';
                    index ++;
                }
                if (cur <= pre) {
                    return false;
                }
                pre = cur;
            } else {
                index ++;
            }
        }

        return true;
    }
}

