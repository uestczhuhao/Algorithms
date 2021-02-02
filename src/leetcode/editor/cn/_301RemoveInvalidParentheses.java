package leetcode.editor.cn;

//删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
//
// 说明: 输入可能包含了除 ( 和 ) 以外的字符。
//
// 示例 1:
//
// 输入: "()())()"
//输出: ["()()()", "(())()"]
//
//
// 示例 2:
//
// 输入: "(a)())()"
//输出: ["(a)()()", "(a())()"]
//
//
// 示例 3:
//
// 输入: ")("
//输出: [""]
// Related Topics 深度优先搜索 广度优先搜索
// 👍 362 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class _301RemoveInvalidParentheses {
    public static void main(String[] args) {
        Solution t = new _301RemoveInvalidParentheses().new Solution();
        String s = "";
        System.out.println(t.removeInvalidParentheses(s));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：bfs，一层层删除，每次删除一个括号，判断剩余的字符串是否合法
         * 依据题意，要删除最少的括号，那么第一次有合法值即可返回，此时删除的字符最少，剩余的最多
         * TODO：可以采用dfs，在leetcode的输入下更快
         */
        public List<String> removeInvalidParentheses(String s) {
            List<String> result = new ArrayList<>();
            if (s == null) {
                return result;
            }

            // 存放字符串和其左右括号数量，如：((() -> [3,1]
            Set<String> curLevelParentheses = new HashSet<>();
            curLevelParentheses.add(s);
            while (!curLevelParentheses.isEmpty()) {
                // 如果当前层已经有合法的字符串，则直接输出即可
                for (String parentheses : curLevelParentheses) {
                    if (isValidPair(parentheses)) {
                        result.add(parentheses);
                    }
                }
                if (!result.isEmpty()) {
                    return result;
                }
                HashSet<String> nextLevelParentheses = new HashSet<>();
                for (String curStr : curLevelParentheses) {
                    for (int i = 0; i < curStr.length(); i++) {
                        String subStr = curStr.substring(0, i) + curStr.substring(i + 1);
                        if (curStr.charAt(i) == '(' || curStr.charAt(i) == ')') {
                            nextLevelParentheses.add(subStr);
                        }
                    }
                }
                curLevelParentheses = nextLevelParentheses;
            }

            return result;
        }

        private boolean isValidPair(String parentheses) {
            int left = 0;
            for (int i=0;i<parentheses.length();i++) {
                if (parentheses.charAt(i) == '(') {
                    left ++;
                }
                if (parentheses.charAt(i) == ')') {
                    left --;
                }
                // 右括号更多，已经非法，可以提前退出
                if (left < 0) {
                    return false;
                }
            }
            return left == 0;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}