package LeetCode;

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
            numStack.push(iNum);

            while (!opStack.isEmpty() && (opStack.peek() == '*' || opStack.peek() == '/')) {
                int firstNum = numStack.pop();
                int secondNum = numStack.pop();
                char iOpt = opStack.pop();
                int res = iOpt == '*' ? firstNum * secondNum : secondNum / firstNum;
                numStack.push(res);
            }

            if (i < len) {
                opStack.push(s.charAt(i));
                i++;
            }

        }

        while (!opStack.isEmpty()) {
            int firstNum = numStack.removeLast();
            int secondNum = numStack.removeLast();
            if (opStack.removeLast() == '+') {
                numStack.addLast(firstNum + secondNum);
            } else {
                numStack.addLast(firstNum - secondNum);
            }
        }

        return numStack.pop();

    }

    public static void main(String[] args) {
        _227BasicCalculatorII t = new _227BasicCalculatorII();
        String s = "0-2147483647";
//        String s = "1-1+1";
        System.out.println(t.calculate(s));
//        System.out.println( 0 - Integer.MAX_VALUE);
    }
}
