package leetcode.editor.cn;

//给定一个经过编码的字符串，返回它解码后的字符串。 
//
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。 
//
// 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。 
//
// 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。 
//
// 
//
// 示例 1： 
//
// 输入：s = "3[a]2[bc]"
//输出："aaabcbc"
// 
//
// 示例 2： 
//
// 输入：s = "3[a2[c]]"
//输出："accaccacc"
// 
//
// 示例 3： 
//
// 输入：s = "2[abc]3[cd]ef"
//输出："abcabccdcdcdef"
// 
//
// 示例 4： 
//
// 输入：s = "abc3[cd]xyz"
//输出："abccdcdcdxyz"
// 
// Related Topics 栈 深度优先搜索 
// 👍 648 👎 0


import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class _394DecodeString {
    public static void main(String[] args) {
        Solution t = new _394DecodeString().new Solution();
//        String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        String s = "2[abc]3[cd]ef";
        System.out.println(t.decodeString(s));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String decodeString(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            LinkedList<Integer> numStack = new LinkedList<>();
            LinkedList<String> strStack = new LinkedList<>();

            int i = 0;
            while (i < s.length()) {
                char curCh = s.charAt(i);
                int copyNum = 0;
                if (curCh >= '0' && curCh <= '9') {
                    do {
                        copyNum = 10 * copyNum + curCh - '0';
                        i++;
                        curCh = s.charAt(i);
                    } while (curCh >= '0' && curCh <= '9');
                    numStack.push(copyNum);
                } else if (curCh == '[') {
                    strStack.push(String.valueOf(s.charAt(i++)));
                } else if (Character.isLetter(curCh)) {
                    StringBuilder sb = new StringBuilder();
                    do {
                        sb.append(curCh);
                        ++i;
                        if (i > s.length() - 1) {
                            break;
                        }
                        curCh = s.charAt(i);
                    } while (Character.isLetter(curCh));
                    strStack.push(sb.toString());
                } else {
                    i++;
                    LinkedList<String> sub = new LinkedList<>();
                    while (!"[".equals(strStack.peek())) {
                        sub.addLast(strStack.pop());
                    }
                    Collections.reverse(sub);

                    // 弹出'['
                    strStack.pop();
                    int num = numStack.pop();
                    String str = getString(sub);
                    String copyStr = copyStr(str, num);
                        strStack.push(copyStr);
                }
            }

            Collections.reverse(strStack);
            return getString(strStack);

        }

        public String getString(LinkedList<String> v) {
            StringBuilder ret = new StringBuilder();
            for (String s : v) {
                ret.append(s);
            }
            return ret.toString();
        }


        private String copyStr(String str, int num) {
            if (num <= 0) {
                return "";
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < num; i++) {
                sb.append(str);
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}