package leetcode;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class _17LetterComPhNum {

    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return res;


        doCombination("", digits, 0, res);

        return res;
    }

    private void doCombination(String pre, String digits, int offset, List<String> res) {
        if (offset >= digits.length()) {
            res.add(pre);
            return;
        }
        String letters = KEYS[digits.charAt(offset) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            doCombination(pre + letters.charAt(i), digits, offset + 1, res);
        }
    }

    private static String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private List<String> ans = new ArrayList<>();
    private StringBuilder combination = new StringBuilder();

    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0) {
            return ans;
        }
        dfs(digits, 0);
        return ans;
    }

    /**
     * 回溯，每次到digits的最后则把答案输出
     * @param index 记录当前应该遍历第几个数，把这个数对应的字符从前往后扫一遍
     */
    public void dfs(String digits, int index) {
        if (index >= digits.length()) {
            ans.add(combination.toString());
            return;
        }

        int curNum = digits.charAt(index) - '0';
        String curStr = KEYS[curNum];
        // 遍历当前数字对应的每个字符
        for (int i = 0; i < curStr.length(); i++) {
            combination.append(curStr.charAt(i));
            dfs(digits, index + 1);
            combination.deleteCharAt(combination.length() - 1);
        }
    }

    public static void main(String[] args) {
        _17LetterComPhNum t = new _17LetterComPhNum();

        String digits = "23";
//        System.out.println(t.letterCombinations(digits));
        System.out.println(t.letterCombinations1(digits));
    }

}
