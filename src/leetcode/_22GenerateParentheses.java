package leetcode;

import java.util.ArrayList;
import java.util.List;

public class _22GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();

        doGenerate(list, "", 0, 0, n);
        return list;
    }

    //change to for loop
    public void doGenerate(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }

        if (open < max) {
            doGenerate(list, str + "(", open + 1, close, max);
        }
        if (close < open) {
            doGenerate(list, str + ")", open, close + 1, max);
        }
    }

    List<String> ans = new ArrayList<>();
    StringBuilder curPair = new StringBuilder();

    public List<String> generateParenthesis1(int n) {
        if (n <= 0) {
            return ans;
        }

        generate(n, 0, 0);
        return ans;
    }

    /**
     * dfs，每一层先放左括号，再放右括号
     * 非集合类的回溯，类似于二叉树的dfs，图参考：https://leetcode.cn/problems/generate-parentheses/solutions/35947/hui-su-suan-fa-by-liweiwei1419/?envType=study-plan-v2&envId=top-100-liked
     */
    public void generate(int n, int left, int right) {
        // 剪枝，左括号大于右括号时直接退出
        if (left < right) {
            return;
        }

        if (left == right && left == n) {
            ans.add(curPair.toString());
            return;
        }

        // 左括号小于n时继续
        if (left < n) {
            curPair.append("(");
            generate(n, left + 1, right);
            curPair.deleteCharAt(curPair.length() - 1);
        }

        if (right < n) {
            curPair.append(")");
            generate(n, left, right + 1);
            curPair.deleteCharAt(curPair.length() - 1);
        }
    }

    public static void main(String[] args) {
        _22GenerateParentheses t = new _22GenerateParentheses();

//        System.out.println(t.generateParenthesis(3));
        System.out.println(t.generateParenthesis1(3));
    }
}
