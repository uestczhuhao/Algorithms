package leetcode.editor.cn;

//给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aaabb", k = 3
//输出：3
//解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
// 
//
// 示例 2： 
//
// 
//输入：s = "ababbc", k = 2
//输出：5
//解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由小写英文字母组成 
// 1 <= k <= 105 
// 
// Related Topics 递归 分治算法 Sliding Window 
// 👍 484 👎 0


public class _395LongestSubstringWithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        Solution t = new _395LongestSubstringWithAtLeastKRepeatingCharacters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 分治，如果存在某个字符 ch，它的出现次数大于 0 且小于 k，
         * 则任何包含 ch 的子串都不可能满足要求
         */
        int maxNum = 0;

        public int longestSubstring(String s, int k) {
            int len = s.length();
            maxNum = k;
            return dfs(s, 0, len - 1);
        }

        private int dfs(String s, int start, int end) {
            int[] chs = new int[26];
            for (int i = start; i <= end; i++) {
                chs[s.charAt(i) - 'a']++;
            }

            char split = 0;
            for (int i = 0; i < 26; i++) {
                if (chs[i] > 0 && chs[i] < maxNum) {
                    split = (char) (i + 'a');
                    break;
                }
            }

            // 如果不存在出现次数小于maxNum的记录
            // 表明这段已经符合要求了
            if (split == 0) {
                return end - start + 1;
            }

            int ans = 0;
            int index = start;
            while (index <= end) {
                while (index <= end && s.charAt(index) == split) {
                    index ++;
                }
                if (index > end) {
                    break;
                }

                int left = index;
                while (index <= end && s.charAt(index) != split) {
                    index ++;
                }

                int curLen = dfs(s, left, index - 1);
                ans = Math.max(ans, curLen);

            }


            return ans;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}