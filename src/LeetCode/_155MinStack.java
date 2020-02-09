package LeetCode;

import java.util.LinkedList;

/**
 * @author mizhu
 * @date 20-2-9 上午10:43
 * <p>
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class _155MinStack {
    public static void main(String[] args) {
        _155MinStack t = new _155MinStack();
        MinStack stack = t.new MinStack();
        stack.push(1);
        stack.push(0);
        stack.push(-1);
        System.out.println(stack.getMin());
        System.out.println(stack.top());
        stack.pop();
    }

    class MinStack {

        private LinkedList<Integer> dataStack = new LinkedList<>();
        private LinkedList<Integer> minStack = new LinkedList<>();
        private int size;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            size = 0;
        }

        public void push(int x) {
            if (minStack.isEmpty()) {
                dataStack.push(x);
                minStack.push(x);
                return;
            }

            dataStack.push(x);
            if (minStack.peek() != null
                    && x < minStack.peek()) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek());
            }
            size ++;
        }

        public void pop() {
            if (dataStack.size() != minStack.size()) {
                throw new RuntimeException("data stack size not equal min stack");
            }
            if (!dataStack.isEmpty()) {
                dataStack.pop();
                minStack.pop();
                size --;
            }
        }

        public int top() {
            return dataStack.isEmpty() ? Integer.MIN_VALUE : dataStack.peek();
        }

        public int getMin() {
            return minStack.isEmpty() ? Integer.MIN_VALUE : minStack.peek();
        }
    }
}
