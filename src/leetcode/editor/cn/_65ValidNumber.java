package leetcode.editor.cn;

//有效数字（按顺序）可以分成以下几个部分： 
//
// 
// 一个 小数 或者 整数 
// （可选）一个 'e' 或 'E' ，后面跟着一个 整数 
// 
//
// 小数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 下述格式之一：
// 
// 至少一位数字，后面跟着一个点 '.' 
// 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字 
// 一个点 '.' ，后面跟着至少一位数字 
// 
// 
// 
//
// 整数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 至少一位数字 
// 
//
// 部分有效数字列举如下： 
//
// 
// ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1",
// "53.5e93", "-123.456e789"] 
// 
//
// 部分无效数字列举如下： 
//
// 
// ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"] 
// 
//
// 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "0"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "e"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s = "."
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = ".1"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。 
// 
// Related Topics 数学 字符串 
// 👍 172 👎 0


public class _65ValidNumber {
    public static void main(String[] args) {
        Solution t = new _65ValidNumber().new Solution();
    }

    /**
     * 题解：https://leetcode-cn.com/problems/valid-number/solution/biao-qu-dong-fa-by-user8973/
     * 有限状态机
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int make(char c) {
            switch (c) {
                case ' ':
                    return 0;
                case '+':
                case '-':
                    return 1;
                case '.':
                    return 3;
                case 'e':
                    return 4;
                default:
                    if (c >= 48 && c <= 57){
                        return 2;
                    }
            }
            return -1;
        }

        public boolean isNumber(String s) {
            int state = 0;
            int finals = 0b101101000;
            int[][] transfer = new int[][]{{0, 1, 6, 2, -1},
                {-1, -1, 6, 2, -1},
                {-1, -1, 3, -1, -1},
                {8, -1, 3, -1, 4},
                {-1, 7, 5, -1, -1},
                {8, -1, 5, -1, -1},
                {8, -1, 6, 3, 4},
                {-1, -1, 5, -1, -1},
                {8, -1, -1, -1, -1}};
            char[] ss = s.toCharArray();
            for (char c : ss) {
                int id = make(c);
                if (id < 0) {
                    return false;
                }
                state = transfer[state][id];
                if (state < 0) {
                    return false;
                }
            }
            return (finals & (1 << state)) > 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}