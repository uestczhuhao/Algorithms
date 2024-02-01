package leetcode.editor.cn;

//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
//
// 返回 s 所有可能的分割方案。
//
// 示例:
//
// 输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//]
// Related Topics 深度优先搜索 动态规划 回溯算法
// 👍 486 👎 0


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _131PalindromePartitioning {
    public static void main(String[] args) {
        Solution t = new _131PalindromePartitioning().new Solution();
        String s = "aabab";
        System.out.println(t.partition(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：回溯法，记录&判断每个子串是否是回文串
         * 注意：判断回文的方法可以提升至O(1)，这样总的时间复杂度能从O(n^3) 提升到 O(n^2)
         */
        int len = 0;
        String str;
        List<List<String>> answer = new LinkedList<>();

        public List<List<String>> partition1(String s) {
            if (s == null || s.length() == 0) {
                return answer;
            }
            str = s;
            len = s.length();
            dfs(0, new LinkedList<>());
            return answer;
        }

        private void dfs(int start, Deque<String> path) {
            if (start == len) {
                answer.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < len; i++) {
                if (isPalindrome(start, i)) {
                    path.add(str.substring(start, i + 1));
                    dfs(i + 1, path);
                    path.removeLast();
                }
            }
        }

        private boolean isPalindrome(int start, int end) {
            return dp[start][end];
            // O(n) 的解法
//            if (start == end) {
//                return true;
//            }
//
//            while (start < end) {
//                if (str.charAt(start) != str.charAt(end)) {
//                    return false;
//                }
//                start++;
//                end--;
//            }

//            return true;
        }

        /**
         * 思路：借助动态规划求回文字符串的思想
         * 预先求出s的子串是否为回文子串，其中dp[i][j]表示字符串的i～j之间的子串是否为回文
         * dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j]
         */
        boolean[][] dp;

        public List<List<String>> partition(String s) {
            if (s == null || s.length() == 0) {
                return answer;
            }
            str = s;
            len = s.length();
            dp = new boolean[len + 1][len + 1];
            //由于dp[i][j]依赖dp[left+1][right-1]的结果，因此左边界从大到小，右边界从小到大
            for (int left = len - 1; left >= 0; left--) {
                for (int right = left; right <= len - 1; right++) {
                    // right - left <= 2 表示三种情况
                    // left和right二者中间只有一个元素；没有元素；边界重合，三种情况统一处理
                    if (str.charAt(left) == str.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                        dp[left][right] = true;
                    }
                }
            }

            dfs(0, new LinkedList<>());

            // 存放结果，dp[i][j] 表示从i到j的字符串是否为回文子串
            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
