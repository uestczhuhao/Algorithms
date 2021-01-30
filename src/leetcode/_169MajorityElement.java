package leetcode;

import java.util.HashMap;

/**
 * @author mizhu
 * @date 2021/1/29 17:53
 */
public class _169MajorityElement {
    public int majorityElement1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        HashMap<Integer, Integer> numCountMap = new HashMap<>(nums.length);
        for (int n : nums) {
            Integer count = numCountMap.getOrDefault(n, 0);
            numCountMap.put(n,  count + 1);
            if (count + 1 > nums.length / 2) {
                return n;
            }
        }

        return Integer.MIN_VALUE;
    }

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        // 候选者，众数的性质会保证候选者最后一定是众数
        int majCandidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                majCandidate = nums[i];
            }

            if (nums[i] == majCandidate) {
                count++;
            } else {
                count--;
            }
        }

        return majCandidate;
    }


}
