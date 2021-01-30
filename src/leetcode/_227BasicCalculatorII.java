package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 2020/11/15 17:24
 * <p>
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * <p>
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 * <p>
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 * <p>
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class _227BasicCalculatorII {
    boolean negative = false;

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return Integer.MIN_VALUE;
        }

        Deque<Integer> numStack = new LinkedList<>();
        Deque<Character> opStack = new LinkedList<>();
        s = s.replaceAll("\\s+", "");
        int i = 0, len = s.length();
        while (i < len) {
            int iNum = 0;

            while (i < len && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                iNum = 10 * iNum + s.charAt(i) - '0';
                i++;
            }
            // 1-3 类型的数，直接放入1和-3，最后累加即可
            if (negative) {
                iNum *= -1;
            }
            numStack.push(iNum);

            /*
            思路：遇到乘除时，先入栈，待下一个操作数入栈后，直接计算
             */
            while (!opStack.isEmpty() && (opStack.peek() == '*' || opStack.peek() == '/')) {
                int firstNum = numStack.pop();
                int secondNum = numStack.pop();
                char iOpt = opStack.pop();
                int res = iOpt == '*' ? firstNum * secondNum : secondNum / firstNum;
                numStack.push(res);
            }

            if (i < len) {
                negative = (s.charAt(i) == '-');
                opStack.push(s.charAt(i));
                i++;
            }
        }

        // 此时不需要压栈，直接累加求和即可
        // 节省14ms（27ms～13ms）
        int result = 0;
        while (numStack.size() > 0) {
            result += numStack.pop();
        }

        return result;

    }

    public static void main(String[] args) {
        _227BasicCalculatorII t = new _227BasicCalculatorII();
//        String s = "0-2147483647";
        String s = "1-1-1+2*3";
        System.out.println(t.calculate(s));
//        System.out.println( 0 - Integer.MAX_VALUE);
    }
}
