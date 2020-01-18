package Algorithm4Edition.Chapter1._1_3.stack.queue;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Created by mizhu on 19-12-21 上午9:24
 */
public class _9CompleteOpenParenthesis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String input = scanner.nextLine();
            System.out.println(completeOpenParenthesis(input));

        }
    }

    /**
     * 两个点需要注意
     * 1. 空格 （已解决）
     * 2. 两位及以上的数字的处理 （未解决）
     * @param input input to complete open parenthesis
     * @return
     */
    static String completeOpenParenthesis(String input) {
        char[] chars = input.toCharArray();
        LinkedList<String> opStack = new LinkedList<>();
        LinkedList<String> dataStack = new LinkedList<>();
        for (int i=0; i< chars.length; i++) {
            char ch = chars[i];
            String strCh = String.valueOf(ch);
            if (ch == ' ') {
                continue;
            }

            if (isDigit(ch)) {
                dataStack.push(strCh);
            } else if (isOperate(ch)) {
                opStack.push(strCh);
            } else {
                String dataRight = dataStack.pop();
                String dataLeft = dataStack.pop();
                strCh = "(" +" "+ dataLeft + " "+opStack.pop() +" "+ dataRight+" "+ strCh;
                dataStack.push(strCh);
            }
        }
        return dataStack.pop();
    }

    private static boolean isOperate(char ch) {
        return ch=='+' || ch=='-' || ch=='*' || ch=='/';
    }

    private static boolean isDigit(char ch) {
        return ch>='0' && ch<='9';
    }
}
