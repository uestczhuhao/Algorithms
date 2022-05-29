package leetcode.editor.cn;

/**
 * <p>给你一个字符串 <code>s</code>，请你将 <code>s</code> 分割成一些子串，使每个子串都是回文。</p>
 *
 * <p>返回符合要求的 <strong>最少分割次数</strong> 。</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "aab"
 * <strong>输出：</strong>1
 * <strong>解释：</strong>只需一次分割就可将 <em>s </em>分割成 ["aa","b"] 这样两个回文子串。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a"
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "ab"
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 2000</code></li>
 * <li><code>s</code> 仅由小写英文字母组成</li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 575</li><li>👎 0</li></div>
 */

public class _132PalindromePartitioningIi {
    public static void main(String[] args) {
        Solution t = new _132PalindromePartitioningIi().new Solution();
        String s = "aab";
        System.out.println(t.minCut(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCut(String s) {
            // 第一次动态规划，提前把s的各种回文情况预处理好
            int len = s.length();
            // dp[i][j]表示字符串的第i位到第j位是否为回文
            // dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j]
            boolean[][] dp = new boolean[len + 1][len + 1];
            for (int left = len; left >= 1; left--) {
                for (int right = left; right <= len; right++) {
                    // 当 right和left的字符相同时，只有两种情况dp[left][right]为true
                    // 1. right 和 left之间的距离为2（中间只有一个数，aba） 或 1（中间没有数，aa）或0 （同一个字符，a）
                    if (s.charAt(left - 1) == s.charAt(right - 1) && (right - left <= 2 || dp[left + 1][right - 1])) {
                        dp[left][right] = true;
                    }
                }
            }

            // 第二次动态规划
            // f[i] 表示前i个字符切分的最小次数
            // 1. 如果[0, i]是回文子串，则f[i] = 0
            // 2. 否则，对j < i，如果[j, i] 是回文子串，则f[i] = f[j-1] + 1
            int[] f = new int[len + 1];
            for (int right = 1; right <= len; right++) {
                if (dp[1][right]) {
                    f[right] = 0;
                } else {
                    // i个字符串，不会超过 i-1次切割
                    int min = right - 1;
                    for (int left = 1; left <= right; left++) {
                        // 在[left, right]是回文的情况下，取f[left - 1] + 1的最小值作为f[right]的值
                        if (dp[left][right] && f[left - 1] + 1 < min) {
                            min = f[left - 1] + 1;
                        }
                    }
                    f[right] = min;
                }
            }
            return f[len];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
