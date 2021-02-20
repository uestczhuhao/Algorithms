package leetcode.editor.cn;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3215 👎 0


public class _5LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution t = new _5LongestPalindromicSubstring().new Solution();
        String s = "babad";
        System.out.println(t.longestPalindrome(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 动态规划解法
         * dp[i][j] 代表i到j的字符串是否为回文
         * 转移方程为：dp[i][j] = dp[i+1][j-1] && s[i+1] == s[j-1]
         */
        public String longestPalindrome(String s) {
            if (null == s || s.length() == 0) {
                return "";
            }

            int len = s.length();
            boolean[][] dp = new boolean[len][len];

            int low = 0, high = 0;
            for (int right = 0; right < len; right++) {
                for (int left = 0; left <= right; left++) {
                    // 如果left和right 相邻，则判断二者字符是否相等
                    // 否则范围缩小2再判断
                    if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
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
//leetcode submit region end(Prohibit modification and deletion)

}