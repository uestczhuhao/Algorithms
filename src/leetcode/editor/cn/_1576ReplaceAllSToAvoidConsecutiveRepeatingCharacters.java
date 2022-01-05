package leetcode.editor.cn;

//给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
//
// 注意：你 不能 修改非 '?' 字符。
//
// 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
//
// 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
//
//
//
// 示例 1：
//
// 输入：s = "?zs"
//输出："azs"
//解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两
//个 'z' 。
//
// 示例 2：
//
// 输入：s = "ubv?w"
//输出："ubvaw"
//解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
//
//
// 示例 3：
//
// 输入：s = "j?qg??b"
//输出："jaqgacb"
//
//
// 示例 4：
//
// 输入：s = "??yw?ipkj?"
//输出："acywaipkja"
//
//
//
//
// 提示：
//
//
//
// 1 <= s.length <= 100
//
//
// s 仅包含小写英文字母和 '?' 字符
//
//
// Related Topics 字符串 👍 85 👎 0


public class _1576ReplaceAllSToAvoidConsecutiveRepeatingCharacters {
    public static void main(String[] args) {
        Solution t = new _1576ReplaceAllSToAvoidConsecutiveRepeatingCharacters().new Solution();
        System.out.println(t.modifyString("j?qg??b"));
        System.out.println(t.modifyString("??yw?ipkj?"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String modifyString1(String s) {
            char[] chs = s.toCharArray();
            boolean[] visit = new boolean[26];
            if (chs[0] == '?') {
                chs[0] = s.length() == 1 || chs[1] == 'a' ? 'b' : 'a';
            }
            for (int i = 1; i < chs.length; i++) {
                if (chs[i] == '?') {
                    visit[chs[i - 1] - 'a'] = true;
                    for (int j = 0; j < 26; j++) {
                        if (!visit[j] && (i == chs.length - 1 || chs[i + 1] - 'a' != j)) {
                            chs[i] = (char) (j + 'a');
                        }
                    }
                    visit[chs[i - 1] - 'a'] = false;
                }
            }
            return new String(chs);
        }

        /**
         * 简单思路：? 的候选能最多有3个，即a b c
         *
         * 自己的思路太过于复杂
         */
        public String modifyString(String s) {
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                for (char j = 'a'; j <= 'c' && chs[i] == '?'; j++) {
                    boolean match = true;
                    if (i > 0 && chs[i - 1] == j) {
                        match = false;
                    }
                    if (i < chs.length - 1 && chs[i + 1] == j) {
                        match = false;
                    }
                    if (match) {
                        chs[i] = j;
                        break;
                    }
                }
            }
            return new String(chs);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
