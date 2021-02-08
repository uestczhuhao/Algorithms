package leetcode.editor.cn;

//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串 
// 👍 564 👎 0


public class _43MultiplyStrings {
    public static void main(String[] args) {
        Solution t = new _43MultiplyStrings().new Solution();
        String num1 = "9";
        String num2 = "99";
        System.out.println(t.multiply(num1, num2));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String multiply(String num1, String num2) {
            if (num1 == null || "0".equals(num1)) {
                return "0";
            }

            if (num2 == null || "0".equals(num2)) {
                return "0";
            }

            int len1 = num1.length();
            int len2 = num2.length();
            int[] resArr = new int[len1 + len2];
            for (int i = len1 - 1; i >= 0; i--) {
                int iThNum = num1.charAt(i) - '0';
                for (int j = len2 - 1; j >= 0; j--) {
                    int jThNum = num2.charAt(j) - '0';
                    int sum = iThNum * jThNum + resArr[i + j + 1];
                    // 找规律，num1 的第i位和num2 的第j位乘积的结果
                    // 结果放在 i+j+1位，进位放在高一位，即i+j位
                    resArr[i + j + 1] = sum % 10;
                    resArr[i + j] += sum / 10;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < resArr.length; i++) {
                if (i == 0 && resArr[i] == 0) {
                    continue;
                }
                sb.append(resArr[i]);
            }
            return sb.toString();
        }


        /**
         * 思路：竖式相乘，最后结果累加
         */
        public String multiply1(String num1, String num2) {
            if (num1 == null || "0".equals(num1)) {
                return "0";
            }

            if (num2 == null || "0".equals(num2)) {
                return "0";
            }

            int len1 = num1.length();
            int len2 = num2.length();
            String answer = "";
            for (int i = len1 - 1; i >= 0; i--) {
                int iThNum = num1.charAt(i) - '0';
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < len1 - 1 - i; j++) {
                    tmp.append(0);
                }

                int carry = 0;
                for (int j = len2 - 1; j >= 0; j--) {
                    int jThNum = num2.charAt(j) - '0';
                    int tmpResult = iThNum * jThNum + carry;
                    tmp.append(tmpResult % 10);
                    carry = tmpResult / 10;
                }
                if (carry > 0) {
                    tmp.append(carry);
                }

                answer = addStrings(answer, tmp.reverse().toString());
            }

            return answer;
        }

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