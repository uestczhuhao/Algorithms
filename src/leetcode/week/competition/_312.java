package leetcode.week.competition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class _312 {
    public static void main(String[] args) {
        _312 t = new _312();
        String[] names = {"Mary", "John", "Emma"};
        int[] hs = {180, 165, 170};
//        System.out.println(Arrays.toString(t.sortPeople(names, hs)));
        int[] nums = {253747, 459932, 263592, 354832, 60715, 408350, 959296};
        int[] nums1 = {2, 1, 1, 2};
        int[] nums2 = {2, 1, 1, 1, 3, 4, 1};
        int[] nums3 =
            {34250, 751275, 914297, 51571, 32780, 4335, 1424, 1131, 49, 3, 3, 3, 2, 2, 2, 1, 1, 1, 585223, 838005, 986277, 994420, 997144, 999009, 999792, 999903, 999976, 999989, 1000000, 1000000,
                1000000, 1000000, 1000000, 1000000, 1000000};
//        System.out.println(t.goodIndices(nums, 2));
//        System.out.println(t.goodIndices(nums1, 2));
//        System.out.println(t.goodIndices(nums2, 2));
//        System.out.println(t.goodIndices(nums3, 16));
        int[] ns = {1,2,3,3,2,2};
        int[] ns2 = {1,1,1};
        int[] ns1 =
            {395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808,
                395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 395808, 470030,
                470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030, 470030,
                153490, 330001, 330001, 330001, 330001, 330001, 330001, 330001, 37284, 470030, 470030, 470030, 470030, 470030, 470030, 156542, 226743};
//        System.out.println(t.longestSubarray(ns));
        System.out.println(t.longestSubarray(ns2));
//        System.out.println(t.longestSubarray(ns1));
    }

    public int longestSubarray(int[] nums) {
        int max = nums[0];
        for (int n : nums) {
            max = Math.max(n, max);
        }

        int ans = 1;
        int count = 0;
        for (int n : nums) {
            if (n == max) {
                count ++;
            } else {
                ans = Math.max(ans, count);
                count = 0;
            }
        }
        return Math.max(ans, count);
    }

    public List<Integer> goodIndices(int[] nums, int k) {
        List<Integer> ans = new LinkedList<>();
        LinkedList<Integer> preList = new LinkedList<>();
        LinkedList<Integer> posList = new LinkedList<>();
        int len = nums.length;
        if (k + k >= len) {
            return ans;
        }
        for (int i = 1; i < k; i++) {
            if (nums[i] > nums[i - 1]) {
                preList.add(i);
            }
        }
        for (int i = k + 2; i <= 2 * k; i++) {
            if (nums[i] < nums[i - 1]) {
                posList.add(i);
            }
        }
        if (preList.isEmpty() && posList.isEmpty()) {
            ans.add(k);
        }
        int index = k + 1;
        while (index < len) {
            if (index + k >= len) {
                break;
            }
            while (!preList.isEmpty() && preList.peekFirst() <= index - k) {
                preList.pollFirst();
            }

            while (!posList.isEmpty() && posList.peekFirst() <= index + 1) {
                posList.pollFirst();
            }
            if (k != 1 && nums[index - 1] > nums[index - 2]) {
                preList.add(index - 1);
            }
            if (k != 1 && nums[index + k] < nums[index + k - 1]) {
                posList.add(index + k);
            }
            if (preList.isEmpty() && posList.isEmpty()) {
                ans.add(index);
            }

            index++;
        }
        return ans;
    }

    public String[] sortPeople(String[] names, int[] heights) {
        TreeMap<Integer, String> treeMap = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < names.length; i++) {
            treeMap.put(heights[i], names[i]);
        }

        return treeMap.values().toArray(new String[0]);
    }
}
