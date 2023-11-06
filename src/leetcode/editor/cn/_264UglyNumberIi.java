package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class _264UglyNumberIi {
    public static void main(String[] args) {
        Solution t = new _264UglyNumberIi().new Solution();
        System.out.println(t.nthUglyNumber(20));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 已知所有的丑数都是由前面的丑数*2 或 *3 或 *5得来的
         * 假设有3个由丑数组成的有序数组，要生成后面的丑数，就都要*2 或 *3 或 *5
         * [a1, a2, a3, a4, a5, ...] * 2
         * [a1, a2, a3, a4, a5, ...] * 3
         * [a1, a2, a3, a4, a5, ...] * 5
         * 要把上面的3个有序数组合并，则声明3个指针，开始都指向a1（a1=1），然后寻找下一个丑数
         * 下一个丑数一定在a1*2 a1*3 a1*5中得出，把结果放入ans中，令其为a2，此时i2=a2，i3=i5=a1
         * <p>
         * https://leetcode.cn/problems/ugly-number-ii/solutions/110653/san-zhi-zhen-fang-fa-de-li-jie-fang-shi-by-zzxn/
         */
        public int nthUglyNumber(int n) {
            int[] ans = new int[n];
            ans[0] = 1;
            int i2 = 0, i3 = 0, i5 = 0, index = 1;
            for (; index < n; index++) {
                int can1 = ans[i2] * 2;
                int can2 = ans[i3] * 3;
                int can3 = ans[i5] * 5;
                int curAns = Math.min(can1, Math.min(can2, can3));
                // 这里不能用else if，原因是一样的丑数时，3个指针都需要跳过
                if (curAns == can1) {
                    i2++;
                }
                if (curAns == can2) {
                    i3++;
                }
                if (curAns == can3) {
                    i5++;
                }
                ans[index] = curAns;
            }
            System.out.println(Arrays.toString(ans));
            return ans[n - 1];
        }

        /**
         * 借助小顶堆，当前元素弹出时，把它的*2 *3 *5的值入堆，当弹出的元素个数为n时，即可返回结果
         */
        public int nthUglyNumber1(int n) {
            Set<Long> set = new HashSet<>();
            PriorityQueue<Long> queue = new PriorityQueue<>();
            set.add(1L);
            queue.add(1L);
            int[] nums = {2, 3, 5};
            for (int i = 1; i <= n; i++) {
                long cur = queue.poll();
                if (i == n) {
                    return (int) cur;
                }
                for (int num : nums) {
                    if (!set.contains(cur * num)) {
                        set.add(cur * num);
                        queue.offer(cur * num);
                    }
                }
            }
            return -1;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
