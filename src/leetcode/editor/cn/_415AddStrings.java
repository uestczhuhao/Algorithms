package leetcode.editor.cn;

//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。 
//
// 
//
// 提示： 
//
// 
// num1 和num2 的长度都小于 5100 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式 
// 
// Related Topics 字符串 
// 👍 305 👎 0


public class _415AddStrings {
    public static void main(String[] args) {
        Solution t = new _415AddStrings().new Solution();
        String num1 = "999";
        String num2 = "9";
        System.out.println(t.addStrings(num1, num2));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addStrings(String num1, String num2) {
            if (num1 == null || "".equals(num1)) {
                return num2;
            }

            if (num2 == null || "".equals(num2)) {
                return num1;
            }

            int len1 = num1.length();
            int len2 = num2.length();
            StringBuilder result = new StringBuilder();
            int carry = 0;
            int i = len1 - 1, j = len2 - 1;
            while (i >= 0 || j >= 0 || carry > 0) {
                int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
                int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
                int sum = n1 + n2 + carry;
                result.append(sum % 10);
                carry = sum / 10;
                i--;
                j--;
            }

            return result.reverse().toString();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}