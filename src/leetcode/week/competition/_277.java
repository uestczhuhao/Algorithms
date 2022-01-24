package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _277 {

    public int countElements(int[] nums) {
        Arrays.sort(nums);
        if (nums.length <= 2) {
            return 0;
        }
        int count = 0, min = nums[0], max = nums[nums.length - 1];
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > min && nums[i] < max) {
                count++;
            }
        }
        return count;
    }

    public int[] rearrangeArray(int[] nums) {
        int[] positive = new int[nums.length / 2];
        int[] negative = new int[nums.length / 2];
        int pIndex = 0, nIndex = 0;
        for (int num : nums) {
            if (num > 0) {
                positive[pIndex++] = num;
            } else {
                negative[nIndex++] = num;
            }
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = i % 2 == 0 ? positive[i / 2] : negative[i / 2];
        }
        return ans;
    }

    public List<Integer> findLonely(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> numFreqMap = new HashMap<>();
        for (int n : nums) {
            numFreqMap.put(n, numFreqMap.getOrDefault(n, 0) + 1);
        }
        for (int n : nums) {
            if (numFreqMap.get(n) == 1 && !numFreqMap.containsKey(n - 1) && !numFreqMap.containsKey(n + 1)) {
                ans.add(n);
            }
        }
        return ans;
    }
}
