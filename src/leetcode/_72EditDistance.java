package leetcode;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 20-3-19 下午9:08
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class _72EditDistance {

    public static void main(String[] args) {
//        String w1 = "intention";
        String w1 = "ate";
        String w2 = "sea";
//        String w2 = "execution";
        _72EditDistance t = new _72EditDistance();
        System.out.println(t.min2(w1, w2));
        System.out.println();
        System.out.println(t.minDistance(w1, w2));
    }

    /**
     * 动态规划，dp[i][j] 对应word1的0~i-1 和 word2的0～j-1之间的编辑最小距离
     * 考虑word1的第i个元素和word2的第j个元素
     * 如果word1[i] == word2[j]，则i和j本身并没有带来多余的distance，则dp[i][j] = dp[i-1][j-1]
     * 如果word1[i] != word2[j]，则分情况考虑
     * 1. 把word1的第i个元素删除，dp[i][j] = dp[i-1][j] + 1
     * 2. 把word2的第j个元素删除，dp[i][j] = dp[i][j-1] + 1
     * 3. 把word1的第i个元素替换使得word1[i] == word2[j]（或换j也可以），dp[i][j] = dp[i-1][j-1] + 1
     * 去上诉三种情况的最小值
     */
    public int minDistance(String word1, String word2) {
        if (null == word1 || null == word2) {
            return 0;
        }

        if (word2.length() == 0) {
            return word1.length();
        }
        if (word1.length() == 0) {
            return word2.length();
        }

        int len1 = word1.length();
        int len2 = word2.length();
        int[] prev = new int[len2 + 1];
        int[] now = new int[len2 + 1];

        // 初始化第一行，代表word1为空串，则等效为添加j个与word2相等的字符
        for (int j = 0; j <= len2; j++) {
            now[j] = j;
        }

        System.out.println(Arrays.toString(now));

        for (int i = 1; i <= len1; i++) {
            int[] tmp = prev;
            prev = now;
            now = tmp;
            // 等效为word2为空字符串，添加i个与word1相等的字符
            now[0] = i;
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    now[j] = prev[j - 1];
                } else {
                    now[j] = Math.min(now[j - 1], Math.min(prev[j], prev[j - 1])) + 1;
                }
            }
            System.out.println(Arrays.toString(now));

        }

        return now[len2];
    }

    /**
     * 二维数组解法
     */
    public int min2(String word1, String word2) {
        if (null == word1 || null == word2) {
            return 0;
        }

        if (word2.length() == 0) {
            return word1.length();
        }
        if (word1.length() == 0) {
            return word2.length();
        }
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; ++i){
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; ++j){
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        for (int i = 0; i <= len1; i++) {

            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[len1][len2];

    }
}
