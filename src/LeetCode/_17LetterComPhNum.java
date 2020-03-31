package LeetCode;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 */
public class _17LetterComPhNum {
    private static String[] KEYS = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return res;


        doCombination("",digits,0,res);

        return res;
    }

    private void doCombination(String pre, String digits, int offset, List<String> res){
        if (offset >= digits.length()){
            res.add(pre);
            return;
        }
        String letters = KEYS[digits.charAt(offset) - '0'];
        for (int i=0;i<letters.length();i++){
            doCombination(pre + letters.charAt(i), digits, offset+1, res);
        }
    }

    public static void main(String[] args) {
        _17LetterComPhNum t = new _17LetterComPhNum();

        System.out.println(t.letterCombinations("21"));
    }

}
