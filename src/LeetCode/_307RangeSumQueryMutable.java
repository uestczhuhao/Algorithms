package LeetCode;

/**
 * @author mizhu
 * @date 2020/4/26 22:49
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * <p>
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 * <p>
 * 示例:
 * <p>
 * Given nums = [1, 3, 5]
 * <p>
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 说明:
 * <p>
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 */
public class _307RangeSumQueryMutable {
    /**
     * 直接法，时间复杂度O（n），空间复杂度O（n）
     */
    private static class NumArray {
        private int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public void update(int i, int val) {
            nums[i] = val;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            for (int k = i; k <= j; k++) {
                sum += nums[k];
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray1 numArray1 = new NumArray1(nums);

        System.out.println(numArray1.sumRange(0, 2));
    }

    /**
     * 线段树，时间复杂度O（logn），空间复杂度O（n），采用了2n空间存储线段树
     * 将元素组织成一棵完全二叉树，i元素的左孩子为2i，右孩子为2i+1
     * 注意：i从1开始，到2n-1截止
     */
    private static class NumArray1 {
        int[] lineTree;
        int len;

        public NumArray1(int[] nums) {
            if (nums == null || nums.length == 0) {
                throw new RuntimeException("num illegal");
            }
            len = nums.length;
            lineTree = new int[2 * len];
            buildTree(nums);
        }

        private void buildTree(int[] nums) {
            for (int i = len; i < 2 * len; i++) {
                lineTree[i] = nums[i - len];
            }

            for (int i = len - 1; i > 0; i--) {
                lineTree[i] = lineTree[2 * i] + lineTree[2 * i + 1];
            }
        }

        public void update(int i, int val) {
            int position = i + len;
            lineTree[position] = val;
            position /= 2;

            while (position > 0) {
                lineTree[position] = lineTree[position * 2] + lineTree[position * 2 + 1];
                position /= 2;
            }
        }

        public int sumRange(int i, int j) {
            if (i < 0 || j >= 2 * len || i > j) {
                throw new RuntimeException("illegal params");
            }
            int left = i + len;
            int right = j + len;
            int sum = 0;

            while (left <= right) {
                if ((left % 2) == 1) {
                    sum += lineTree[left];
                    left++;
                }
                if ((right % 2) == 0) {
                    sum += lineTree[right];
                    right--;
                }

                left /= 2;
                right /= 2;
            }
            return sum;
        }
    }
}
