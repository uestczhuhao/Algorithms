package leetcode.editor.cn;

//给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd", k = 2
//输出："bacd"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由小写英文组成 
// 1 <= k <= 104 
// 
// Related Topics 双指针 字符串 
// 👍 180 👎 0


public class _541ReverseStringIi {
    public static void main(String[] args) {
        Solution t = new _541ReverseStringIi().new Solution();
        String s = "abcdefgh";
        int k = 3;
        System.out.println(t.reverseStr(s, k));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseStr(String s, int k) {
            int index = 0;
            char[] chs = s.toCharArray();
            while (index + k < s.length()) {
                changeRangeChars(chs, index, index + k - 1);
                index += 2 * k;
            }

            if (index < s.length()) {
                changeRangeChars(chs, index, s.length() - 1);
            }

            return new String(chs);
        }

        private void changeRangeChars(char[] chs, int start, int end) {
            while (start <= end) {
                char tmp = chs[start];
                chs[start] = chs[end];
                chs[end] = tmp;
                start++;
                end--;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}