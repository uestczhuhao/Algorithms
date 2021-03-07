package leetcode.editor.cn;

//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
//句子。 
//
// 说明： 
//
// 
// 分隔时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// 示例 2： 
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
// 
// Related Topics 动态规划 回溯算法 
// 👍 415 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _140WordBreakIi {
    public static void main(String[] args) {
        Solution t = new _140WordBreakIi().new Solution();
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = new ArrayList<>(Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"));
        System.out.println(t.wordBreak(s, wordDict));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
//    class Solution {
//
//    }


    public class Solution {

        /**
         * 思路：先做dp，判断s的前i个字符能否拆分，用作剪枝
         * 然后做dfs，把s拆分成 前缀 + 单个单词 的组合，如果前缀能拆分（dp保证），则把单词加入结果
         *
         * 自底向上，用动态规划剪枝
         */
        public List<String> wordBreak(String s, List<String> wordDict) {
            // 为了快速判断一个单词是否在单词集合中，需要将它们加入哈希表
            Set<String> wordSet = new HashSet<>(wordDict);
            int len = s.length();

            // 第 1 步：动态规划计算是否有解
            // dp[i] 表示「长度」为 i 的 s 前缀子串可以拆分成 wordDict 中的单词
            // 长度包括 0 ，因此状态数组的长度为 len + 1
            boolean[] dp = new boolean[len + 1];
            // 0 这个值需要被后面的状态值参考，如果一个单词正好在 wordDict 中，dp[0] 设置成 true 是合理的
            dp[0] = true;

            for (int right = 1; right <= len; right++) {
                // 如果单词集合中的单词长度都不长，从后向前遍历是更快的
                for (int left = right - 1; left >= 0; left--) {
                    // substring 不截取 s[right]，dp[left] 的结果不包含 s[left]
                    if (wordSet.contains(s.substring(left, right)) && dp[left]) {
                        dp[right] = true;
                        // 这个 break 很重要，一旦得到 dp[right] = True ，不必再计算下去
                        break;
                    }
                }
            }

            // 第 2 步：回溯算法搜索所有符合条件的解
            List<String> res = new ArrayList<>();
            if (dp[len]) {
                Deque<String> path = new ArrayDeque<>();
                dfs(s, len, wordSet, dp, path, res);
                return res;
            }
            return res;
        }

        /**
         * s[0:len) 如果可以拆分成 wordSet 中的单词，把递归求解的结果加入 res 中
         *
         * @param len     长度为 len 的 s 的前缀子串
         * @param wordSet 单词集合，已经加入哈希表
         * @param dp      预处理得到的 dp 数组
         * @param path    从叶子结点到根结点的路径
         * @param res     保存所有结果的变量
         */
        private void dfs(String s, int len, Set<String> wordSet, boolean[] dp, Deque<String> path, List<String> res) {
            if (len == 0) {
                res.add(String.join(" ", path));
                return;
            }

            // 可以拆分的左边界从 len - 1 依次枚举到 0
            for (int i = len - 1; i >= 0; i--) {
                String suffix = s.substring(i, len);
                if (wordSet.contains(suffix) && dp[i]) {
                    path.addFirst(suffix);
                    dfs(s, i, wordSet, dp, path, res);
                    path.removeFirst();
                }
            }
        }


        /**
         * 思路： 记忆化搜索，把之前的结果（即index为头的部分可以组成的句子列表）缓存下来，下次需要时直接用
         *
         * 自顶向下，每一步计算出结果时缓存，是为记忆化搜索
         */
        public List<String> wordBreak1(String s, List<String> wordDict) {
            Map<Integer, List<List<String>>> map = new HashMap<>();
            List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<>(wordDict), 0, map);
            List<String> breakList = new LinkedList<>();
            for (List<String> wordBreak : wordBreaks) {
                breakList.add(String.join(" ", wordBreak));
            }
            return breakList;
        }

        public List<List<String>> backtrack(String s, int length, Set<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
            if (!map.containsKey(index)) {
                List<List<String>> wordBreaks = new LinkedList<>();
                if (index == length) {
                    wordBreaks.add(new LinkedList<>());
                }
                for (int i = index + 1; i <= length; i++) {
                    String word = s.substring(index, i);
                    if (wordSet.contains(word)) {
                        List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);
                        for (List<String> nextWordBreak : nextWordBreaks) {
                            LinkedList<String> wordBreak = new LinkedList<>(nextWordBreak);
                            wordBreak.offerFirst(word);
                            wordBreaks.add(wordBreak);
                        }
                    }
                }
                map.put(index, wordBreaks);
            }
            return map.get(index);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}