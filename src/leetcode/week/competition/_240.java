package leetcode.week.competition;

import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mizhu
 * @date 2021/5/9 21:17
 */
public class _240 {
    public static void main(String[] args) {
        Test t = new Test();
        int[][] logs = {{1993, 1999}, {2000, 2010}};
//        System.out.println(t.maximumPopulation(logs));
        int[] nums1 = {55, 30, 5, 4, 2}, nums2 = {1, 1};
//        System.out.println(t.maxDistance(nums1, nums2));
        int[] nums = {3, 1, 5, 6, 4, 2};
        System.out.println(t.maxSumMinProduct(nums));
    }

    public int maximumPopulation(int[][] logs) {
        int[] yearPop = new int[100];
        int birth, death;
        for (int[] log : logs) {
            birth = log[0];
            death = log[1];
            for (int i = birth; i < death; i++) {
                yearPop[i - 1950]++;
            }
        }

        int maxPop = 0, year = 0;
        for (int i = 0; i < yearPop.length; i++) {
            if (yearPop[i] > maxPop) {
                maxPop = yearPop[i];
                year = i;
            }
        }

        return year + 1950;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int distance = 0;
        while (i < nums1.length && j < nums2.length) {
            if (i > j) {
                j = i;
                continue;
            }

            if (nums1[i] > nums2[j]) {
                i++;
            } else {
                distance = Math.max(distance, j - i);
                j++;
            }
        }

        return distance;
    }

    public int maxSumMinProduct(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        long max = 0;
        int ans = 0;
        int len = nums.length;
        long[] sum = new long[len + 1];
        sum[0] = 0;
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                int candidate = stack.pop();
                int leftNe = stack.isEmpty() ? -1 : stack.peek();
                long cur = (sum[i] - sum[leftNe + 1]) * nums[candidate];
                if (cur > max) {
                    max = cur;
                    ans = (int) (cur % 1000_000_007);
                }
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int candidate = stack.pop();
            int leftNe = stack.isEmpty() ? -1 : stack.peek();
            long cur = (sum[len] - sum[leftNe + 1]) * nums[candidate];
            if (cur > max) {
                max = cur;
                ans = (int) (cur % 1000_000_007);
            }
        }


        return ans;
    }

    public int largestPathValue(String colors, int[][] edges) {
        HashMap<Integer, Set<Integer>> downStream = new HashMap<>(edges.length);
        HashMap<Integer, Set<Integer>> upStream = new HashMap<>(edges.length);
        for (int[] eg : edges) {
            int src = eg[0];
            int tgt = eg[1];
            Set<Integer> downs = downStream.getOrDefault(src, new HashSet<>());
            downs.add(tgt);
            downStream.put(src, downs);
            Set<Integer> ups = upStream.getOrDefault(tgt, new HashSet<>());
            ups.add(src);
            upStream.put(tgt, ups);
        }

        Set<Integer> allDowns = downStream.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        Set<Integer> allUps = upStream.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        allDowns.retainAll(allUps);
        Set<Integer> zeroIn = allDowns;

        int max = 0;
        int[] colorNum;
        for (int root : zeroIn) {
            colorNum = new int[26];
            int curNum = computeMaxColor(downStream, root, colorNum, colors);
            max = Math.max(curNum, max);
        }

        return max;
    }

    private int computeMaxColor(HashMap<Integer, Set<Integer>> downStream, int node, int[] colorNum, String colorStr) {
        char ch;

    }
}
