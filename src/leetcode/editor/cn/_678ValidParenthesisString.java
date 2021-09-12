package leetcode.editor.cn;

//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则： 
//
// 
// 任何左括号 ( 必须有相应的右括号 )。 
// 任何右括号 ) 必须有相应的左括号 ( 。 
// 左括号 ( 必须在对应的右括号之前 )。 
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 示例 1: 
//
// 
//输入: "()"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "(*)"
//输出: True
// 
//
// 示例 3: 
//
// 
//输入: "(*))"
//输出: True
// 
//
// 注意: 
//
// 
// 字符串大小将在 [1，100] 范围内。 
// 
// Related Topics 栈 贪心 字符串 动态规划 
// 👍 365 👎 0


import java.util.Deque;
import java.util.LinkedList;

public class _678ValidParenthesisString {
    public static void main(String[] args) {
        Solution t = new _678ValidParenthesisString().new Solution();
        System.out.println(t.checkValidString("(*))"));
        System.out.println(t.checkValidString("())*"));
        System.out.println(t.checkValidString("("));
        System.out.println(t.checkValidString(")"));
        System.out.println(t.checkValidString("(((((*(((((*((**(((*)*((((**))*)*)))))))))"));
        System.out.println(t.checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkValidString(String s) {
            // 左括号下标
            Deque<Integer> leftStack = new LinkedList<>();
            // 右括号下标
            Deque<Integer> matchStack = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '(') {
                    leftStack.push(i);
                } else if (ch == '*') {
                    matchStack.push(i);
                } else {
                    // 优先匹配左括号，左括号都匹配完成后，再匹配*；若二者都匹配完了，证明右括号多了，直接返回false
                    if (!leftStack.isEmpty()) {
                        leftStack.pop();
                    } else if (!matchStack.isEmpty()){
                        matchStack.pop();
                    } else {
                        return false;
                    }
                }
            }
            if (leftStack.size() > matchStack.size()) {
                return false;
            }

            // 上诉都是把*当成左括号用，当*还没用完时，可以当右括号用
            // 注意：这种情况下要保证*的下标在（ 的右边，否则就是左括号太多了，没有与之匹配的项（右括号或*），返回false
            while (!leftStack.isEmpty()) {
                int leftIndex = leftStack.pop();
                int matchIndex = matchStack.pop();
                if (leftIndex > matchIndex) {
                    return false;
                }
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}