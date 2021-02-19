package leetcode.editor.cn;

//给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。 
//
// 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串： 
//
// 
// s = s1 + s2 + ... + sn 
// t = t1 + t2 + ... + tm 
// |n - m| <= 1 
// 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ... 
// 
//
// 提示：a + b 意味着字符串 a 和 b 连接。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s1 = "", s2 = "", s3 = ""
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s1.length, s2.length <= 100 
// 0 <= s3.length <= 200 
// s1、s2、和 s3 都由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 400 👎 0


import java.util.Arrays;

public class _97InterleavingString {
    public static void main(String[] args) {
        Solution t = new _97InterleavingString().new Solution();
        String a = "aabcc";
        String b = "dbbca";
        String c = "aadbbcbcac";
        System.out.println(t.isInterleave(a, b, c));
        System.out.println(t.isInterleave1(a, b, c));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：动态规划，dp[i][j] 表示 s1的前i个元素和s2的前j个元素是否能组成s3的前i+j个元素
         * dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i + j - 1]) || (dp[i][j - 1] && s2[j - 1] == s3[i + j - 1])
         */
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1 == null || s1.length() == 0) {
                return s2.equals(s3);
            }

            if (s2 == null || s2.length() == 0) {
                return s1.equals(s3);
            }

            int len1 = s1.length();
            int len2 = s2.length();

            // 若前两个字符串长度和不等于第三个字符串，直接返回错误
            if (len1 + len2 != s3.length()) {
                return false;
            }
            boolean[][] dp = new boolean[len1 + 1][len2 + 1];
            dp[0][0] = true;

            // 遇到不相等字符后，后面的字符串不可能符合要求
            for (int i = 1; i <= len1 && s1.charAt(i - 1) == s3.charAt(i - 1); i++) {
                dp[i][0] = true;
            }

            // 遇到不相等字符后，后面的字符串不可能符合要求
            for (int j = 1; j <= len2 && s2.charAt(j - 1) == s3.charAt(j - 1); j++) {
                dp[0][j] = true;
            }
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
            return dp[len1][len2];
        }

        /**
         * 空间优化，使用滚动数组
         */
        public boolean isInterleave1(String s1, String s2, String s3) {
            if (s1 == null || s1.length() == 0) {
                return s2.equals(s3);
            }

            if (s2 == null || s2.length() == 0) {
                return s1.equals(s3);
            }

            int len1 = s1.length();
            int len2 = s2.length();

            // 若前两个字符串长度和不等于第三个字符串，直接返回错误
            if (len1 + len2 != s3.length()) {
                return false;
            }
            boolean[] dp = new boolean[len2 + 1];
            dp[0] = true;

            for (int i = 0; i <= len1; i++) {
                for (int j = 0; j <= len2; j++) {
                    if (i > 0) {
                        dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    }
                    if (j > 0) {
                        dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[len2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}