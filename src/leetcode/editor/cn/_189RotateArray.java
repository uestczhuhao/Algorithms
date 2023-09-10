package leetcode.editor.cn;

import java.util.Arrays;

public class _189RotateArray {
    public static void main(String[] args) {
        Solution t = new _189RotateArray().new Solution();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        t.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 终极大招，翻转数组，第一次：把nums全部翻转；第二次：前k个翻转；第三次，后n-k个翻转
        // 原理：当我们将数组的元素向右移动 k次后，尾部 k % n 个元素会移动至数组头部，其余元素向后移动 k % n 个位置。
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            if (k >= n) {
                k %= n;
            }
            reverse(nums, 0, n - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, n - 1);
        }

        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
                start++;
                end--;
            }

        }


        // 时间复杂度O(n)，空间复杂度O(1)
        // 用tmp记录换下来的值，count记录已经遍历的元素个数
        public void rotate2(int[] nums, int k) {
            int tmp, count = 0;
            int index = 0, n = nums.length;
            while (count < n) {
                int start = index;
                int pre = nums[start];
                // 循环这里注意：当index回到初始位置时，要再做一次替换
                // 原因是第一次start = index是开始位置，此时start位置的值还是原值，此后start位置的值都没有变化
                // 第二次start = index时，需要把原来start的位置的旧值替换成新值（此时的pre）
                do {
                    tmp = nums[(index + k) % n];
                    nums[(index + k) % n] = pre;
                    pre = tmp;
                    index = (index + k) % n;
                    count++;
                    if (count >= n) {
                        return;
                    }
                } while (start != index);
                index = (index + 1) % n;
            }
        }

        // 时间复杂度O(n)，空间复杂度O(n)
        public void rotate1(int[] nums, int k) {
            int n = nums.length;
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                ans[(i + k) % n] = nums[i];
            }
            System.arraycopy(ans, 0, nums, 0, n);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
