package LeetCode;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 2020/4/1 21:13
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 */
public class _44WildcardMatching {
    public static void main(String[] args) {
        String s = "";
        String p = "*";
        System.out.println(isMatch(s, p));
    }

    /**
     * 动态规划，dp[i][j] 为s的前i个字符和p的前j个字符是否匹配
     * <p>
     * 1. 如果p[i] == '*'，'*'可以代表0，1，2，3...个任意字符，因此
     * 0个字符，d[i][j] = dp[i][j-1]，即直接不考虑p[j]
     * 1个字符，d[i][j] = dp[i-1][j]
     * 2个字符，d[i][j] = dp[i-2][j]
     * .....
     * 上诉的结果，有一个为真，则dp[i][j]就为真
     * <p>
     * 2. 如果p[i] == '?'，'?'可以代表任意一个字符，因此d[i][j] = dp[i-1][j-1]
     * <p>
     * 3. 其余情况，d[i][j] = dp[i-1][j-1] && s[i] == p[j]
     */
    public static boolean isMatch(String s, String p) {
        // 如果p为空，只有s为空时可能匹配
        if (p.isEmpty()) {
            return s.equals(p);
        }

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int pIndex = 1; pIndex <= p.length(); pIndex++) {
            char ch = p.charAt(pIndex - 1);
            if (ch == '*') {
                dp[0][pIndex] = dp[0][pIndex - 1];
            }
            for (int sIndex = 1; sIndex <= s.length(); sIndex++) {
                if (ch == '*') {
                    dp[sIndex][pIndex] = dp[sIndex - 1][pIndex] || dp[sIndex][pIndex - 1];
                } else if (ch == '?' || s.charAt(sIndex - 1) == ch) {
                    // 情况2和3合并成一种
                    dp[sIndex][pIndex] = dp[sIndex - 1][pIndex - 1];
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}
