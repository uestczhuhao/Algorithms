package leetcode;

import java.util.Random;

/**
 * @author mizhu
 * @date 20-2-1 下午8:36
 * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1, 5, 1, 1, 6, 4]
 * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 * 示例 2:
 * <p>
 * 输入: nums = [1, 3, 2, 2, 3, 1]
 * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 * 说明:
 * 你可以假设所有输入都会得到有效的结果。
 * <p>
 * 进阶:
 * 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class _324WiggleSort2 {
    public static void main(String[] args) {
        _324WiggleSort2 t = new _324WiggleSort2();
//        int[] nums = {3, 2, 4, 1, 5, 6};
        int[] nums = {1, 1, 2, 2, 2, 1};
        t.wiggleSort(nums);
    }

    /**
     * 1. 先找到数组的中位数mid，借助快排的partition
     * 2. 通过3-way-partition，把数组分为三部分a b c，其中a中元素比mid小，b中与mid相等，c中比mid大
     * 3. 插空重排原数组，注意是逆序插空，否则，如果中位数个数太多，还是容易出现中位数相邻的情况
     */
    public void wiggleSort(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }

        int length = nums.length;
        int middleNum = findKthNum(nums, length / 2);
        threeWayPartition(nums, middleNum);
        int leftLen = (nums.length + 1) / 2;
        int rightLen = nums.length - leftLen;
        int[] leftNums = new int[leftLen];
        int[] rightNums = new int[rightLen];
        System.arraycopy(nums, 0, leftNums, 0, leftLen);
        System.arraycopy(nums, leftLen, rightNums, 0, rightLen);
        for (int i = 0; i < leftLen; i++) {
            nums[2 * i] = leftNums[leftLen - 1 - i];
        }
        for (int i = 0; i < rightLen; i++) {
            nums[2 * i + 1] = rightNums[rightLen - 1 - i];
        }
    }

    /**
     * 将数组分为小于，等于和大于target的三个部分
     */
    private void threeWayPartition(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        if (low >= high) {
            return;
        }
        int scanIndex = low;
        while (scanIndex < high) {
            if (nums[scanIndex] < target) {
                swap(nums, scanIndex, low);
                low++;
                scanIndex++;
            } else if (nums[scanIndex] > target) {
                // 从高位交换过来的元素还未处理过，所以scanIndex不往前移动
                swap(nums, scanIndex, high);
                high--;
            } else {
                scanIndex++;
            }
        }
    }

    /**
     * 找到数组中位于k位置的数
     */
    private int findKthNum(int[] nums, int k) {
        //设定一个锚点
        int low = 0, high = nums.length - 1;
        int position = low;
        while (low <= high) {
            position = partition(nums, low, high);
            if (position == k) {
                break;
            } else if (position > k) {
                high = position - 1;
            } else {
                low = position + 1;
            }
        }
        return nums[k];
    }

    /**
     * 随机选定一个锚点，将数组依据这个锚点分为两部分，左边比它都小，右边比它都大
     */
    private int partition(int[] nums, int low, int high) {
        if (low >= high) {
            return low;
        }
        int index = low + new Random().nextInt(high - low + 1);
        swap(nums, low, index);
        // 辅助值，用于采用赋值而非交换
        int assist = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= assist) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] <= assist) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = assist;
        return low;
    }

    private void swap(int[] nums, int source, int target) {
        int temp = nums[source];
        nums[source] = nums[target];
        nums[target] = temp;
    }
}
