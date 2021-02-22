package leetcode.editor.cn;

//给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。 
//
// 说明： 
//
// 
// 拆分时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
// 
//
// 示例 2： 
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
// Related Topics 动态规划 
// 👍 841 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _139WordBreak {
    public static void main(String[] args) {
        Solution t = new _139WordBreak().new Solution();
        List<String> words = new ArrayList<>();
        words.add("leet");
        words.add("code");
        boolean b = t.wordBreak1("leetcode", words);
        System.out.println(b);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 动态规划，
         * dp[i]代表前i个字符组成的字符串能否被分割
         * dp[i] = dp[j] && check(j..i)
         * 其中check函数代表分割点处到i的字符串是否包含在字典中
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            if (s == null) {
                return false;
            }

            int len = s.length();
            HashSet<String> wordSet = new HashSet<>(wordDict);
            boolean[] dp = new boolean[len + 1];
            dp[0] = true;

            for (int i = 1; i <= len; i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[len];
        }

        public boolean wordBreak1(String s, List<String> wordDict) {
            if (s == null) {
                return false;
            }

            int totalLen = s.length();
            int maxWordLen = 0;
            HashSet<String> wordSet = new HashSet<>();
            for (String w : wordDict) {
                maxWordLen = Math.max(maxWordLen, w.length());
                wordSet.add(w);
            }
            boolean[] dp = new boolean[totalLen + 1];
            dp[0] = true;

            for (int i = 1; i <= totalLen; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    // 增加剪枝，字符串长度大于字典候选中最长者，不可能再匹配上
                    if (i - j > maxWordLen) {
                        break;
                    }
                    if (dp[j] && wordSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[totalLen];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}