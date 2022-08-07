package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class _305 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 4, 6, 7, 10};
        _305 t = new _305();
//        System.out.println(t.arithmeticTriplets(nums, 3));
        int[][] edges = {{0, 1}, {0, 2}, {0, 5}, {0, 4}, {3, 2}, {6, 5}};
        int[] restricted = {4, 2, 1};
//        System.out.println(t.reachableNodes(7, edges, restricted));
        int[] nums1 = {348054, 7876, 34051};
//        System.out.println(t.validPartition(nums1));
        String s = "acfgbd";
        System.out.println(t.longestIdealString(s, 2));
    }

    /**
     * dp[i]表示字符串前i位（0~i-1）的最长理想子序列长度的值
     * 假设s的第i位字符是si，则dp[i]可以从i前面的子序列推导而来，但子序列的结尾必须在以[si - k, si + k]为结尾的子序列之后
     * 可以考虑把某字符i结尾的最长理想子序列的长度记录在数组len中
     * 注意：实现时dp可以被省略，理由是每个位置i的最优解都同步到了len数组中
     */
    public int longestIdealString(String s, int k) {
        int n = s.length();
        // len数组隐含了"过去"的信息
        // 因为i从小到大遍历，当遍历到i+1时，前i个字符已经处理完成且存放至len中了
        int[] len = new int[26];
        for (int i = 0; i < n; i++) {
            int curIndex = s.charAt(i) - 'a';
            for (int j = Math.max(0, curIndex - k); j <= Math.min(25, curIndex + k); j++) {
                len[curIndex] = Math.max(len[curIndex], len[j]);
            }
            len[curIndex]++;
        }

        return Arrays.stream(len).max().getAsInt();
    }

    public boolean validPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        boolean[] dp = new boolean[nums.length + 1];
        dp[0] = true;
        dp[2] = valid(nums, 0, 1);
        for (int i = 3; i <= nums.length; i++) {
            dp[i] = (dp[i - 2] && valid(nums, i - 2, i - 1)) || (dp[i - 3] && valid(nums, i - 3, i - 1));
        }
        return dp[nums.length];
    }

    private boolean valid(int[] nums, int start, int end) {
        if (end - start == 1) {
            return nums[start] == nums[end];
        } else {
            return (nums[start] == nums[start + 1] && nums[start + 1] == nums[end])
                || (nums[start] + 1 == nums[start + 1] && nums[start + 1] + 1 == nums[end]);
        }
    }


    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        HashMap<Integer, Set<Integer>> nextNodesMap = new HashMap<>();
        for (int[] ed : edges) {
            int start = ed[0];
            int end = ed[1];
            nextNodesMap.putIfAbsent(start, new HashSet<>());
            nextNodesMap.putIfAbsent(end, new HashSet<>());
            nextNodesMap.get(start).add(end);
            nextNodesMap.get(end).add(start);
        }

        Set<Integer> curStep = nextNodesMap.get(0);
        Set<Integer> nextStep = new HashSet<>();
        Set<Integer> ans = new HashSet<>();
        ans.add(0);
        Set<Integer> visited = Arrays.stream(restricted).boxed().collect(Collectors.toSet());
        visited.add(0);
        while (!curStep.isEmpty()) {
            for (Integer curNode : curStep) {
                if (!visited.contains(curNode)) {
                    visited.add(curNode);
                    nextStep.addAll(nextNodesMap.get(curNode));
                    ans.add(curNode);
                }
            }
            curStep = nextStep;
            nextStep = new HashSet<>();
        }
        return ans.size();
    }

    public int arithmeticTriplets(int[] nums, int diff) {
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n - diff) && set.contains(n - 2 * diff)) {
                ans++;
            }
            set.add(n);
        }
        return ans;
    }
}
