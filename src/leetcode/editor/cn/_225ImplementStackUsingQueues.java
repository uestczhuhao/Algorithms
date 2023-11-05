package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

public class _225ImplementStackUsingQueues {
    public static void main(String[] args) {
//        Solution t = new _225ImplementStackUsingQueues().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyStack1 {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();

        public MyStack1() {

        }

        public void push(int x) {
            queue1.offer(x);
        }

        public int pop() {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            int ans = queue1.poll();
            Queue<Integer> tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
            return ans;
        }

        public int top() {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            int ans = queue1.peek();
            queue2.offer(queue1.poll());
            Queue<Integer> tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
            return ans;
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    class MyStack {
        // 思路：queue1中存放逆序的数字，即和栈中保持一致
        // 如：插入 1 2 3 4，queue1中的元素为 4 3 2 1
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();

        public MyStack() {

        }

        // push时，先插入到queue2，再把queue1的元素依次放进queue2，再交换
        // 每次push都这么操作，等效为每次把最后放入的元素放到队列头，最后实现了逆序
        public void push(int x) {
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            Queue<Integer> tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
        }

        public int pop() {
            return queue1.poll();
        }

        public int top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
