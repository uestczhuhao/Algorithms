package leetcode.editor.cn;

public class _303RangeSumQueryImmutable {
    public static void main(String[] args) {
//        Solution t = new _303RangeSumQueryImmutable().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {
        int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
            for (int i = 1; i < nums.length; i++) {
                nums[i] += nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) {
                return nums[right];
            } else {
                return nums[right] - nums[left - 1];
            }
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
