package InterviewGuide;

import java.util.EmptyStackException;
import java.util.Stack;

public class _1GetMinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public _1GetMinStack() {
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public Integer push(Integer num) {

        if (stackMin.isEmpty()){
            stackMin.push(num);
        } else {
            if (this.getMin() >= num){
                stackMin.push(num);
            }
        }

        return stackData.push(num);
    }

    public Integer pop() {
        if (stackData.empty()){
            throw new RuntimeException("Your stack is empty");
        }

        if (this.getMin().equals(stackData.peek())){
            stackMin.pop();
        }

        return stackData.pop();
    }

    public Integer getMin() {
        if (stackMin.empty()){
            throw new RuntimeException("Your stack is empty");
        }

        return stackMin.peek();
    }

    public static void main(String[] args) {
        _1GetMinStack1 stack = new _1GetMinStack1();

        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(1);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.getMin());

        stack.pop();
        stack.pop();
        stack.pop();

        System.out.println(stack.getMin());

    }
}


class _1GetMinStack1 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public _1GetMinStack1() {
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public Integer push(Integer num) {
        if (stackMin.isEmpty()){
            stackMin.push(num);
        } else if (this.getMin() >= num) {
            stackMin.push(num);
        } else {
            stackMin.push(stackMin.peek());
        }

        return stackData.push(num);
    }

    public Integer pop() {
        if (stackMin.isEmpty() || stackData.isEmpty()){
            throw new IllegalArgumentException("Your stack is empty");
        }

        stackMin.pop();
        return stackData.pop();
    }

    public Integer getMin() {
        if (stackMin.isEmpty()){
            throw new IllegalArgumentException("Your stack is empty");
        }

        return stackMin.peek();
    }


}
