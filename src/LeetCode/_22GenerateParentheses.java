package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _22GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();

        doGenerate(list,"", 0,0,n);
        return list;
    }

    //change to for loop
    public void doGenerate(List<String> list, String str, int open, int close, int max){
        if (str.length() == max *2){
            list.add(str);
            return;
        }

        if(open < max){
            doGenerate(list,str+"(" ,open+1, close, max);
        }
        if (close < open) {
            doGenerate(list, str+")", open, close+1, max);
        }
    }

    public static void main(String[] args) {
        _22GenerateParentheses t = new _22GenerateParentheses();

        System.out.println(t.generateParenthesis(3));
    }
}
