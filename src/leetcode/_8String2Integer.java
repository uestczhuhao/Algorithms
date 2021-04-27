package leetcode;


/**
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * <p>
 * Note:
 * <p>
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * Example 1:
 * <p>
 * Input: "42"
 * Output: 42
 * Example 2:
 * <p>
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 * Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 * <p>
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 * <p>
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 * <p>
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Thefore INT_MIN (−231) is returned.
 */


public class _8String2Integer {
    public int myAtoi11(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int len = s.length();
        int index = 0;
        // 先判断str的前面有没有空格，有的话跳过
        for (; index < s.length(); index++) {
            if (s.charAt(index) != ' ') {
                break;
            }
        }

        // 去除空格之后，看是否已经到末尾了，如果到末尾了代表s只有空格，返回0
        if (index >= len) {
            return 0;
        }
        boolean positive = true;
        // 跳过正负号
        if (s.charAt(index) == '-') {
            index++;
            positive = false;
        } else if (s.charAt(index) == '+') {
            index++;
        }

        long result = 0;
        // 往后遍历，直到遍历到非数字的部分跳出循环
        while (index < len && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
            int curNum = s.charAt(index) - '0';
            result = 10 * result + curNum;
            if (result > Integer.MAX_VALUE) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            index++;
        }

        return positive ? (int) result : (int) -result;
    }

    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }
        int sign = 1, start = 0, len = str.length();
        long sum = 0;
        if (str.charAt(0) == '-') {
            sign = -1;
            start = 1;
        } else if (str.charAt(0) == '+') {
            start = 1;
        }

        while (start < len) {
            if (!Character.isDigit(str.charAt(start))) {
                return (int) sum * sign;
            }
            sum = sum * 10 + str.charAt(start++) - '0';
            if (sign == 1 && sum > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            if (sign == -1 && (-1) * sum < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) sum * sign;
    }

    public static void main(String[] args) {
        _8String2Integer t = new _8String2Integer();
        System.out.println(t.myAtoi11("42"));
        System.out.println(t.myAtoi11("+42"));
        System.out.println(t.myAtoi11("   -42"));
        System.out.println(t.myAtoi11("4193 with words"));
        System.out.println(t.myAtoi11("words and 987"));
        System.out.println(t.myAtoi11("-91283472332"));
        System.out.println(t.myAtoi11("+91283472332"));
        System.out.println(t.myAtoi11("91283472332"));
        System.out.println(t.myAtoi11(" "));
    }
}
