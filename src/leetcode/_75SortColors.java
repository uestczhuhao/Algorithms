package leetcode;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 20-1-25 下午8:19
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class _75SortColors {
    public static void main(String[] args) {
//        int[] nums = {2,0,2,1,1,0};
//        int[] nums = {1,2,0};
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }
        int pointOfZero = 0;
        int current = 0;
        int pointOfTwo = nums.length - 1;
        // 如果超过了2的边界，则不需要再继续交换了
        while (current <= pointOfTwo) {
            if (nums[current] == 0) {
                // 当前位置元素为0，则交换其与0处元素
                // 注意此时current是大于等于pointOfZero的，因此pointOfZero处的值只可能为1（因为如果是2的话会被交换到后面去）
                swap(nums, current, pointOfZero);
                pointOfZero++;
                current++;
            } else if (nums[current] == 2) {
                // 如果当前值为2，则交换其与此时2下标位置的元素
                // 注意交换后仍然可能为任何值，因此current不往前走
                swap(nums, current, pointOfTwo);
                pointOfTwo--;
            } else {
                // current为1，跳过不处理
                current++;
            }
        }

    }

    private static void swap(int[] nums, int src, int dst) {
        if (src == dst) {
            return;
        }

        int temp = nums[src];
        nums[src] = nums[dst];
        nums[dst] = temp;
    }
}
