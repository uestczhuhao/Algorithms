package leetcode.week.competition;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class _315 {
    public static void main(String[] args) {
        _315 t = new _315();
        int[] nums = {-1, 10, 6, 7};
//        System.out.println(t.findMaxK(nums));
        int[] a = {1,3,5,2,7,5};
        System.out.println(t.countSubarrays(a, 1, 5));
//        System.out.println(t.sumOfNumberAndReverse(0));
    }

    /**
     * 定界子数组满足性质：
     * 1. 子数组不能包含越界的数字（nums[i] > maxK 或 nums[i] < minK）
     * 2. 子数组必须同时包含 maxK 和 minK。
     *
     * 根据上述条件，我们从左到右遍历数组，统计以 i 为右端点的定界子数组数量：
     * 1. 维护左侧第一个越界数字的位置 invalid，表示左端点不能等于或小于 invalid；
     * 2. 同时，分别维护 maxK 和 minK 在左侧第一次出现的位置 leftMin和leftMax
     * 3. 因此，以 i 为右边界的子数组数量（如果存在）= Math.min(leftMax, leftMin) - invalid
     */
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0L;
        int invalid = -1;
        int leftMax = -1, leftMin = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                invalid = i;
            }
            if (minK == nums[i]) {
                leftMin = i;
            }
            if (maxK == nums[i]) {
                leftMax = i;
            }
            ans += Math.max(Math.min(leftMax, leftMin) - invalid, 0);
        }
        return ans;
    }

    public boolean sumOfNumberAndReverse(int num) {
        for (int i = 0; i <= num; i++) {
            int reverseI = Integer.parseInt(new StringBuilder(String.valueOf(i)).reverse().toString());
            if (i + reverseI == num) {
                return true;
            }
        }
        return false;
    }

    public int countDistinctIntegers(int[] nums) {
        Set<Integer> ans = new HashSet<>();
        for (int num : nums) {
            ans.add(num);
            ans.add(Integer.parseInt(new StringBuilder(String.valueOf(num)).reverse().toString()));
        }
        return ans.size();
    }

    public int findMaxK(int[] nums) {
        int ans = -1;
        int[] can = new int[1010];
        for (int num : nums) {
            if (num > 0) {
                if (can[num] < 0) {
                    can[num] += num;
                    ans = Math.max(num, ans);
                } else {
                    can[num] = num;
                }
            } else {
                can[-num] += num;
                if (can[-num] == 0) {
                    ans = Math.max(-num, ans);
                }
            }
        }
        return ans;
    }
}
