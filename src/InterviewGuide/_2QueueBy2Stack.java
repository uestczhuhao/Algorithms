package InterviewGuide;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class _2QueueBy2Stack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> stackReverse = new Stack<>();

    public boolean add(Integer num) {
        return stack.add(num);
    }

    public Integer poll() {
        if (stack.isEmpty() && stackReverse.isEmpty()) {
            return null;
        } else if (stackReverse.isEmpty()) {
            while (!stack.isEmpty()){
                stackReverse.push(stack.pop());
            }
        }
        return stackReverse.pop();
    }

    public Integer peek() {
        if (stack.isEmpty() && stackReverse.isEmpty()) {
            return null;
        } else if (stackReverse.isEmpty()) {
            while (!stack.isEmpty()){
                stackReverse.push(stack.pop());
            }
        }
        return stackReverse.peek();
    }
    public static void main(String[] args) {
        _2QueueBy2Stack que = new _2QueueBy2Stack();
        que.add(1);
        que.add(2);
        que.add(3);
        que.add(5);
        que.add(7);
        System.out.println(que.stack);
        System.out.println(que.poll());
        System.out.println(que.poll());
        System.out.println("stackReverse  "+que.stackReverse);
        que.add(11);
        System.out.println(que.poll());
        System.out.println("stackReverse  "+que.stackReverse);
        que.add(12);
        System.out.println(que.poll());
        System.out.println(que.poll());
        System.out.println("stackReverse  "+que.stackReverse);
        System.out.println(que.poll());
        System.out.println("stackReverse  "+que.stackReverse);
        System.out.println("peek " +que.peek());
        System.out.println("stackReverse  "+que.stackReverse);
        System.out.println(que.poll());

        System.out.println(que.peek());
    }
}
