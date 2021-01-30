package leetcode;

import java.util.Stack;

public class _20ValidParentheses {
    public boolean isValid(String s) {
        char[] chs = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '{' || chs[i] == '[' || chs[i] == '(') {
                stack.push(chs[i]);
                continue;
            }
            if (chs[i] == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                return false;
            }
            if (chs[i] == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                return false;
            }

            if (chs[i] == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        _20ValidParentheses t = new _20ValidParentheses();

        System.out.println(t.isValid("()[]{}"));
        System.out.println(t.isValid("(]"));
        System.out.println(t.isValid("([)]"));
        System.out.println(t.isValid("([])"));
    }
}
