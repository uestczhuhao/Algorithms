package leetcode.editor.cn;

/**
 * <p>给定一个字符串 <code>s</code><strong> </strong>和一个字符串 <code>t</code> ，计算在 <code>s</code> 的子序列中 <code>t</code> 出现的个数。</p>
 *
 * <p>字符串的一个 <strong>子序列</strong> 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，<code>"ACE"</code> 是 <code>"ABCDE"</code> 的一个子序列，而 <code>"AEC"</code> 不是）</p>
 *
 * <p>题目数据保证答案符合 32 位带符号整数范围。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "rabbbit", t = "rabbit"<code>
 * <strong>输出</strong></code><strong>：</strong><code>3
 * </code><strong>解释：</strong>
 * 如下图所示, 有 3 种可以从 s 中得到 <code>"rabbit" 的方案</code>。
 * <code><strong><u>rabb</u></strong>b<strong><u>it</u></strong></code>
 * <code><strong><u>ra</u></strong>b<strong><u>bbit</u></strong></code>
 * <code><strong><u>rab</u></strong>b<strong><u>bit</u></strong></code></pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "babgbag", t = "bag"
 * <code><strong>输出</strong></code><strong>：</strong><code>5
 * </code><strong>解释：</strong>
 * 如下图所示, 有 5 种可以从 s 中得到 <code>"bag" 的方案</code>。
 * <code><strong><u>ba</u></strong>b<u><strong>g</strong></u>bag</code>
 * <code><strong><u>ba</u></strong>bgba<strong><u>g</u></strong></code>
 * <code><u><strong>b</strong></u>abgb<strong><u>ag</u></strong></code>
 * <code>ba<u><strong>b</strong></u>gb<u><strong>ag</strong></u></code>
 * <code>babg<strong><u>bag</u></strong></code>
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= s.length, t.length <= 1000</code></li>
 * <li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 767</li><li>👎 0</li></div>
 */

public class _115DistinctSubsequences {
    public static void main(String[] args) {
        Solution t = new _115DistinctSubsequences().new Solution();
        System.out.println(t.numDistinct("babgbag", "bag"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp[i][j] 代表t的前j个字符 在 s的前i个字符 的子序列中出现的种类数，考虑s的第i个字符
         * 当s[j]不参与比较时，dp[i][j] = dp[i-1][j]
         * 否则，分两种情况，
         * 1. 当s[i] == t[j]时，dp[i][j] += dp[i-1][j-1]
         * 2. s[i] != t[j]，dp[i][j]数量不变
         */
        public int numDistinct(String s, String t) {
            int n = s.length();
            int m = t.length();
            // dp[i][j] 代表t的前j个字符 在 s的前i个字符 的子序列中出现的种类数
            int[][] dp = new int[n + 1][m + 1];
            // t的前0个字符为空字符串，因此s的任意长度都可以有一种子序列（空串）满足条件
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 1;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] += dp[i - 1][j - 1];
                    }
                }
            }
            return dp[n][m];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
