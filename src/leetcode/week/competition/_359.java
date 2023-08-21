package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _359 {
    public static void main(String[] args) {
        _359 t = new _359();
        List<List<Integer>> offers = Arrays.asList(
            Arrays.asList(0, 0, 1),
            Arrays.asList(0, 2, 2),
            Arrays.asList(1, 3, 2)
        );
        System.out.println(t.maximizeTheProfit(5, offers));
    }

    /**
     * 动态规划，f[i+1] 表示销售不超过第i个房子时的最大收益
     * 1. 第i个房子不卖，f[i+1] = f[i]
     * 2. 第i个房子卖，此时需要遍历所有可能性，即所有end_j=i的卖法，f[i+1] = max(f[i]， f[start_j]+gold_j)
     * 解释：对2的可能性，有三种情况：
     * 第一种，有多个end=i的记录，那么取它们之间的最大值（算法为：每种可能性的值是f[start] + gold，根据定义，f[start]代表取当start之前的最大收益，加上卖掉start~i的收益gold）
     * 第二种，没有一个end=i的记录，则取f[i]，因为当前房屋没有卖的可能性，如输入为[1, 3, 2]，当i=0时
     * 第三种，上面两种都有，则取较大值
     */
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        // 优化：把offers按end先存起来
        Map<Integer, List<int[]>> endMap = offers.stream()
            .collect(HashMap::new, (map, list) -> {
                int key = list.get(1);
                int[] value = new int[] {list.get(0), list.get(2)};
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            }, (map1, map2) -> {
                // combiner方法，把两个map合并成一个
                map2.forEach((key, value) -> map1.merge(key, value, (v1, v2) -> {
                    v1.addAll(v2);
                    return v1;
                }));
            });

        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // i房不卖
            dp[i + 1] = dp[i];
            if (!endMap.containsKey(i)) {
                continue;
            }
            // i房卖，取题解中3种情况的最大值
            for (int[] startGold : endMap.get(i)) {
                dp[i + 1] = Math.max(dp[i + 1], dp[startGold[0]] + startGold[1]);
            }
        }
        return dp[n];
    }

    public int minimumSum(int n, int k) {
        Set<Integer> avoidSet = new HashSet<>();
        int ans = 0, cnt = 0, cur = 1;
        while (cnt < n) {
            if (!avoidSet.contains(cur)) {
                ans += cur;
                if (k > cur) {
                    avoidSet.add(k - cur);
                }
                cnt++;
            }
            cur++;
        }
        return ans;
    }

    public boolean isAcronym(List<String> words, String s) {
        if (s.length() != words.size()) {
            return false;
        }

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).charAt(0) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
