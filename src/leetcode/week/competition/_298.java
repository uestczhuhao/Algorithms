package leetcode.week.competition;

import java.util.Map;

public class _298 {
    public static void main(String[] args) {
        _298 t = new _298();
        String s = "AbCdEfGhIjK";
//        System.out.println(t.greatestLetter(s));
//        System.out.println(t.minimumNumbers(4, 0));
//        System.out.println(t.minimumNumbers(10, 0));
//        System.out.println(t.minimumNumbers(18, 3));
//        System.out.println(t.minimumNumbers(4, 2));
//        System.out.println(t.minimumNumbers(1, 1));
//        System.out.println(t.longestSubsequence("1001010", 5));

        System.out.println(t.longestSubsequence(
            "111100010000011101001110001111000000001011101111111110111000011111011000010101110100110110001111001001011001010011010000011111101001101000000101101001110110000111101011000101",
            11713332));
    }

    /**
     * dp[i][j] 表示把长为i，宽为j的木头卖掉所得的最大收益，有三种卖法
     * 1. 直接卖掉，取price[i][j]
     * 2. 横向切割卖掉，dp[i][j] = max{dp[k][j] + dp[i-k][j]}，其中0 < k < i
     * 3. 纵向切割卖掉，dp[i][j] = max{dp[i][z] + dp[i][j-z]}，其中0 < z < j
     * 注意：为了在1中避免每次从prices数组中遍历才能找到prices[i][j]，新建一个pr数组
     * 取第i，j位置的元素为长为i，宽为j的木头的价格，即pr[i][j] = price
     */
    public long sellingWood(int m, int n, int[][] prices) {
        int[][] pr = new int[m + 1][n + 1];
        for (int[] p : prices) {
            pr[p[0]][p[1]] = p[2];
        }
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = pr[i][j];
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }

                for (int z = 1; z < j; z++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][z] + dp[i][j - z]);
                }
            }
        }
        return dp[m][n];
    }

    public int longestSubsequence(String s, int k) {
        s = handleBig(s);
        int value = Integer.parseUnsignedInt(s, 2);
        int ans = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (value <= k) {
                break;
            }
            if (s.charAt(i) == '0') {
                continue;
            }
            value -= (int) Math.pow(2, s.length() - i - 1);
            ans--;
        }

        return ans;
    }

    private String handleBig(String s) {
        if (s.length() < 31) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length() - 30; i++) {
            if (s.charAt(i) == '1') {
                continue;
            }
            sb.append(s.charAt(i));
        }
        sb.append(s.substring(s.length() - 30));
        return sb.toString();
    }

    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        int remain = num % 10;
        if (k == 0) {
            return remain == 0 ? 1 : -1;
        }
        while (remain <= num) {
            if (remain >= k && remain % k == 0) {
                return remain / k;
            }
            remain += 10;
        }
        return -1;
    }

    public String greatestLetter(String s) {
        boolean[] upper = new boolean[26];
        boolean[] lower = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur >= 'a' && cur <= 'z') {
                lower[cur - 'a'] = true;
            } else if (cur >= 'A' && cur <= 'Z') {
                upper[cur - 'A'] = true;
            }
        }

        for (int i = 25; i >= 0; i--) {
            if (upper[i] && lower[i]) {
                char res = (char) ('A' + i);
                return String.valueOf(res);
            }
        }
        return "";
    }
}
