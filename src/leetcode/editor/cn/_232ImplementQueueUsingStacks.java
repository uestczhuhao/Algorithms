package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

public class _232ImplementQueueUsingStacks {
    public static void main(String[] args) {
//        Solution t = new _232ImplementQueueUsingStacks().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyQueue {
        Deque<Integer> pushStack;
        Deque<Integer> popStack;
        Integer peek = null;

        public MyQueue() {
            pushStack = new LinkedList<>();
            popStack = new LinkedList<>();
        }

        public void push(int x) {
            pushStack.push(x);
        }

        public int pop() {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
            int ans = popStack.pop();
            peek = popStack.isEmpty() ? null : popStack.peek();
            // 放回到pushStack的操作也可以在push操作发生时做，防止连续的pop操作
            while (!popStack.isEmpty()) {
                pushStack.push(popStack.pop());
            }
            return ans;
        }

        public int peek() {
            if (peek == null && !pushStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
                peek = popStack.isEmpty() ? null : popStack.peek();
                while (!popStack.isEmpty()) {
                    pushStack.push(popStack.pop());
                }
            }
            return peek;
        }

        public boolean empty() {
            return pushStack.isEmpty() && popStack.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
