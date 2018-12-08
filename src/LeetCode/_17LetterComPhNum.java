package LeetCode;

import java.util.*;

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
