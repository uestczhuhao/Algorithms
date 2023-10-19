package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class _202HappyNumber {
    public static void main(String[] args) {
        Solution t = new _202HappyNumber().new Solution();
        System.out.println(t.isHappy(10));
        System.out.println(t.isHappy(2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isHappy1(int n) {
            Set<Integer> visited = new HashSet<>();
            n = doCompute(n);
            while (!visited.contains(n)) {
                visited.add(n);
                if (n == 1) {
                    return true;
                }
                n = doCompute(n);
            }
            return false;
        }

        /**
         * 如果一定成环的话，用快慢指针的方法，一定会使二者相遇
         */
        public boolean isHappy(int n) {
            int slow = n, fast = n;
            do {
                slow = doCompute(slow);
                fast = doCompute(fast);
                fast = doCompute(fast);
            } while (slow != fast);
            // 如果slow==1，也会触发slow==fast，从而退出循环
            return slow == 1;
        }

        private int doCompute(int num) {
            int ans = 0;
            while (num > 0) {
                int cur = num % 10;
                ans += cur * cur;
                num /= 10;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
