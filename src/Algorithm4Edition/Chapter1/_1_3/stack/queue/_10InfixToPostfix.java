package Algorithm4Edition.Chapter1._1_3.stack.queue;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by mizhu on 19-12-21 上午10:54
 *
 * @author mizhu
 */
public class _10InfixToPostfix {

    static LinkedList<String> oprtStack = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            char[] chars = str.toCharArray();
            for (int i=0; i<chars.length; i++) {
               String strChar = String.valueOf(chars[i]);
                if (" ".equals(strChar)) {
                    continue;
                }
                handleInfix(strChar);
            }
            while (!oprtStack.isEmpty()) {
                System.out.print(oprtStack.pop());
            }

        }
    }

    /**
     * 分四种情况考虑：
     * 1. 输入为加减乘除运算符
     *      a 循环，当（栈非空and栈顶不是开括号and栈顶运算符的优先级不低于输入的运算符的优先级）时，反复操作：将栈顶元素出栈
     *      b 把输入的运算符入栈
     * 2. 输入为左括号
     *      直接入栈
     * 3. 输入为右括号
     *      输出操作符栈中的元素，直到遇到"("
     * 4. 输入为操作数
     *      直接输出
     *
     * 最后，栈内若还有元素，则一次输出
     */
    static void handleInfix(String str){
        if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str)) {
            String topOptr = oprtStack.peek();
            // 当前操作符为+ - 时做如下操作，去掉这个if条件也没发现问题
            if ("+".equals(str) || "-".equals(str)) {
                while (!oprtStack.isEmpty()
                        && ("*".equals(topOptr)) || "/".equals(topOptr)) {
                    System.out.print(oprtStack.pop());
                    topOptr =oprtStack.peek();
                }
            }
            oprtStack.push(str);

        } else if ("(".equals(str)) {
            oprtStack.push(str);
        } else if (")".equals(str)) {
            String op = oprtStack.pop();
            while (!"(".equals(op)) {
                System.out.print(op);
                op = oprtStack.pop();
            }

        } else {
            System.out.print(str);
        }
    }



}
