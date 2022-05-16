package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class _293 {
    public static void main(String[] args) {
        _293 t = new _293();
        String[] words = {"abba", "baba", "bbaa", "cd", "cd"};
//        System.out.println(t.removeAnagrams(words));
        int[] special = {7, 6, 8};
//        System.out.println(t.maxConsecutive(6, 8, special));
//        int[] nums = {16,17,71,62,12,24,14};
        int[] nums = {8, 8};
//        System.out.println(t.largestCombination(nums));
        _293.CountIntervals intervals = new _293().new CountIntervals();
//        System.out.println(intervals.count());
        intervals.add(13, 49);
        intervals.add(20, 30);
        System.out.println(intervals.count());
        intervals.add(47, 50);
        System.out.println(intervals.count());
    }

    private class CountIntervals {
        TreeMap<Integer, int[]> intervalMap = new TreeMap<>();
        int sum = 0;

        public CountIntervals() {

        }

        public void add(int left, int right) {
            // 两个区间相交的条件，包括左边半包，全包和右边半包
            // right2 >= left1 && left2 <= right1
            Map.Entry<Integer, int[]> biggerEntry = intervalMap.ceilingEntry(left);
            while (!Objects.isNull(biggerEntry) && biggerEntry.getValue()[0] <= right) {
                int[] canInterval = biggerEntry.getValue();
                left = Math.min(left, canInterval[0]);
                right = Math.max(right, canInterval[1]);
                intervalMap.remove(biggerEntry.getKey());
                sum -= canInterval[1] - canInterval[0] + 1;
                biggerEntry = intervalMap.ceilingEntry(left);
            }
            sum += right - left + 1;
            intervalMap.put(right, new int[] {left, right});
        }

        public int count() {
            return sum;
        }
    }

    public int largestCombination(int[] candidates) {
        int[] oneNums = new int[32];
        for (int can : candidates) {
            int index = 0;
            while (can > 0) {
                oneNums[index++] += can % 2;
                can /= 2;
            }
        }
//        System.out.println(Arrays.toString(oneNums));
        int max = 0;
        for (int i = 31; i >= 0; i--) {
            max = Math.max(oneNums[i], max);
        }
        return max;
    }

    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int len = special.length;
        int max = Math.max(special[0] - bottom, top - special[len - 1]);
        for (int i = 1; i < len; i++) {
            max = Math.max(special[i] - special[i - 1] - 1, max);
        }
        return max;
    }

    public List<String> removeAnagrams(String[] words) {
        List<String> ans = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            ans.add(words[i]);
            int j = i + 1;
            while (j < words.length && hasSameChar(words[i], words[j])) {
                j++;
            }
            i = j;
        }
        return ans;
    }

    private boolean hasSameChar(String src, String tgt) {
        if (src.length() != tgt.length()) {
            return false;
        }

        char[] sChars = new char[26];
        char[] tChars = new char[26];
        for (int i = 0; i < src.length(); i++) {
            sChars[src.charAt(i) - 'a']++;
            tChars[tgt.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sChars[i] != tChars[i]) {
                return false;
            }
        }
        return true;
    }
}
