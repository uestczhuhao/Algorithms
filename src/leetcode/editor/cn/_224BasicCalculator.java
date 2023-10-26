package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

public class _224BasicCalculator {
    public static void main(String[] args) {
        Solution t = new _224BasicCalculator().new Solution();
//        System.out.println(t.calculate("(1+(4+5+2)-3)+(6+8)"));
//        System.out.println(t.calculate(" 2-1 + 2 "));
//        System.out.println(t.calculate(" 2- (   -1)"));
        System.out.println(t.calculate(" -( 4-1-2)"));
        System.out.println(t.calculate(" -( 4-(1-2))"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：把括号打开，如果括号前面为-号，则把括号里面的正变负，负变正
         * 如：-( 4+1-2) 转变成 -4 - 1 + 2
         * 注：当遇到右括号)时，把括号前面的符号pop掉（相应的，遇到左括号时把符号入栈）
         */
        public int calculate1(String s) {
            Deque<Integer> st = new LinkedList<>();
            int ans = 0, num = 0, op = 1;
            st.push(op);
            char[] chs = s.toCharArray();

            for (char c : chs) {
                if (c == ' ') {
                    continue;
                }
                if (c >= '0') {
                    num = num * 10 - '0' + c;
                } else {
                    ans += op * num;
                    num = 0;

                    if (c == '+') {
                        op = st.peek();
                    } else if (c == '-') {
                        op = -st.peek();
                    } else if (c == '(') {
                        st.push(op); // 将括号前符号放入栈顶
                    } else {
                        st.pop();
                    }
                }
            }

            return ans + op * num;

        }

        public int calculate(String s) {
            Deque<Integer> ops = new LinkedList<>();
            // 运行到i时，应该的符号位，由当前位置（若当前为+ -）或前面的符号位（若当前为数字）与栈顶的值共同决定
            // 若二者相同，则取栈顶，否则取栈顶元素的相反
            int sign = 1;
            // 先加个默认的+号
            ops.push(sign);
            int n = s.length(), i = 0, ans = 0;
            while (i < n) {
                char curCh = s.charAt(i);
                // 当遇到( 时，括号里面的符号位，要由当前位置和括号前面的位置共同决定
                // 如： -(4-(1-2))，第一个(括号后面的4，要由第一个-号决定，是-4
                // 当运行到4右边的-时，它与第一个-号运算，变成+，那么第二个(括号后面的1就是+1，2就是-2
                if (curCh == '(') {
                    ops.push(sign);
                } else if (curCh == '+') {
                    sign = ops.peek();
                } else if (curCh == '-') {
                    sign = -ops.peek();
                } else if (curCh == ')') {
                    ops.pop();
                } else if (curCh >= '0' && curCh <= '9') {
                    long curNum = 0;
                    while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                        curNum = curNum * 10 + s.charAt(i++) - '0';
                    }
                    ans += sign * curNum;
                    continue;
                }
                i++;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
