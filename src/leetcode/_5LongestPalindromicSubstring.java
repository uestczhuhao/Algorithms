package leetcode;

/**
 * @author mizhu
 * @date 2020/3/30 21:37
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class _5LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

    /**
     * 动态规划解法
     * dp[i][j] 代表i到j的字符串是否为回文
     * 转移方程为：dp[i][j] = dp[i+1][j-1] && s[i+1] == s[j-1]
     */
    public static String longestPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }

        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        // i~i 只有一个字符，必定为回文
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int low = 0, high = 0;
        for (int right = 0; right < len; right++) {
            for (int left = 0; left < right; left++) {
                // 如果left和right 相邻，则判断二者字符是否相等
                // 否则范围缩小2再判断
                if (s.charAt(left) == s.charAt(right) && (right - left < 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    if (right - left > high - low) {
                        low = left;
                        high = right;
                    }
                }

            }
        }
        return s.substring(low, high + 1);
    }
}
