package leetcode;

import leetcode.week.competition._361;

import java.util.Deque;
import java.util.LinkedList;

public class ReverseStack {
    public static void main(String[] args) {
        ReverseStack t = new ReverseStack();
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 5; i > 0; i--) {
            stack.push(i);
        }
        System.out.println(stack);
        t.reverse(stack);
        System.out.println(stack);
    }

    // 思路：O(n^2)的思路：reverse函数中，先找到当前栈的底元素（O(n)时间），再把剩下的栈（没有之前的底元素了）翻转，最后把原来的底元素加到顶
    // 再把当前栈翻转 的做法：每次找到栈底元素，把栈底元素push到栈顶，递归调用，就实现了翻转
    public void reverse(Deque<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int value = getBottom(stack);
        reverse(stack);
        stack.push(value);
    }

    // getBottom思路：弹出栈中所有元素（O(n)），再把底元素返回，其他的元素再原样push回去
    private int getBottom(Deque<Integer> stack) {
        int num = stack.pop();
        if (stack.isEmpty()) {
            return num;
        } else {
            int ans = getBottom(stack);
            stack.push(num);
            return ans;
        }
    }
}
