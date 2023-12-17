package leetcode;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

/**
 * @author mizhu
 * @date 20-3-18 下午8:54
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * <p>
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 */
public class _1143LongestCommonSubsequence {
    public static void main(String[] args) {
        _1143LongestCommonSubsequence t = new _1143LongestCommonSubsequence();
        String text1 = "ace";
//        String text1 = "hofubmnylkra";
//        String text1 =  "pmjghexybyrgzczy";
//        String text2 = "hafcdqbgncrcbihkd";
//        String text2 = "pqhgxgdofcvmr";
        String text2 = "abcde";
        System.out.println(t.longestCommonSubsequence0(text1, text2));
    }

    public int longestCommonSubsequence0(String text1, String text2) {

        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * dp[i][j] 代表text1的前i个元素与text2的前j个元素的最长公共子序列
     * 若text1[i] == text2[j]，则dp[i][j] = dp[i-1][j-1] + 1
     * 若text1[i] != text2[j]，则把i去掉，最长子序列不会变短；同理，把j去掉，最长子序列也不会变短
     * 因此，dp[i][j] 是 dp[i-1][j] 和 dp[i][j-1]中间较大的那个值
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (null == text1 || null == text2
            || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1][len2];
        if (text1.charAt(0) == text2.charAt(0)) {
            dp[0][0] = 1;
        }
        for (int i = 1; i < len1; i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int j = 1; j < len2; j++) {
            if (text1.charAt(0) == text2.charAt(j)) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1 - 1][len2 - 1];
    }

    public int longestCommonSubsequence1(String text1, String text2) {
        if (null == text1 || null == text2
            || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int len1 = text1.length();
        int len2 = text2.length();

        // 提升，多一行多一列，第0行和第0列做为冗余，可以避免指针越界，统一做初始化
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }

    /**
     * 二维数组提升为一维
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        if (null == text1 || null == text2
            || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int len1 = text1.length();
        int len2 = text2.length();
        int[] now = new int[len2 + 1];
        int[] prev = new int[len2 + 1];

        for (int i = 1; i <= len1; i++) {
            int[] tmp = now;
            now = prev;
            prev = tmp;
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    now[j] = prev[j - 1] + 1;
                } else {
                    now[j] = Math.max(now[j - 1], prev[j]);
                }
            }
        }

        return now[len2];
    }

    /**
     * 二维数组提升为一维，并不多申请
     */
    public int longestCommonSubsequence3(String text1, String text2) {
        if (null == text1 || null == text2
            || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int len1 = text1.length();
        int len2 = text2.length();
        int[] now = new int[len2];
        int[] prev = new int[len2];

        for (int i = 0; i < len1; i++) {
            int[] tmp = now;
            now = prev;
            prev = tmp;
            if (text1.charAt(i) == text2.charAt(0)) {
                now[0] = 1;
            }
            now[0] = Math.max(now[0], prev[0]);
            for (int j = 1; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    now[j] = prev[j - 1] + 1;
                } else {
                    now[j] = Math.max(now[j - 1], prev[j]);
                }
            }

        }

        return now[len2 - 1];
    }
}

