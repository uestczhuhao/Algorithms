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
//        System.out.println(t.isNumber("0e"));
//        System.out.println(t.isNumber("."));
//        System.out.println(t.isNumber("G76"));
        System.out.println(t.isNumber("11"));
    }

    /**
     * 题解：https://leetcode-cn.com/problems/valid-number/solution/biao-qu-dong-fa-by-user8973/
     * 有限状态机
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1. 根据e或E分开
         * 若不包含e，则字符串可以为 整数 或 小数
         * 若包含1个e，把字符串分为前部分和后面部分，前面部分可以是整数 或 小数，后面部分只能是整数
         * 若包含2个或多个e，非法，直接返回false
         * 判断是否为小数
         * + -只能出现在字符串的头部
         * 只包含一个.
         * 至少包含一个数字
         */
        public boolean isNumber(String s) {
            char[] chs = s.toCharArray();
            int size = chs.length;
            int eIndex = -1;
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == 'e' || chs[i] == 'E') {
                    if (eIndex == -1) {
                        eIndex = i;
                    } else { // 存在两个或以上的e
                        return false;
                    }
                }
            }

            if (eIndex == -1) {
                return checkNumber(chs, size - 1);
            } else {
                return checkNumber(chs, eIndex - 1) && checkInteger(chs, eIndex + 1, size - 1);
            }
        }

        private boolean checkInteger(char[] chs, int start, int end) {
            if (start > end) {
                return false;
            }
            if (chs[start] == '+' || chs[start] == '-') {
                start++;
            }

            boolean hasNum = false;
            while (start <= end) {
                if (chs[start] >= '0' && chs[start] <= '9') {
                    hasNum = true;
                } else {
                    return false;
                }
                start++;
            }
            return hasNum;
        }

        private boolean checkNumber(char[] chs, int end) {
            int start = 0;
            if (start > end) {
                return false;
            }
            if (chs[start] == '+' || chs[start] == '-') {
                start++;
            }
            boolean dot = false;
            boolean hasNum = false;
            while (start <= end) {
                if (chs[start] == '.') {
                    if (!dot) {
                        dot = true;
                    } else {
                        return false;
                    }
                } else if (chs[start] >= '0' && chs[start] <= '9') {
                    hasNum = true;
                } else {
                    return false;
                }
                start++;
            }
            return hasNum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
