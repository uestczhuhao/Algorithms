package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author mizhu
 * @date 20-2-8 下午3:12
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 */
public class _315CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        _315CountOfSmallerNumbersAfterSelf t = new _315CountOfSmallerNumbersAfterSelf();
//        int[] nums = {1, 2, 0};
//        int[] nums = {-1, -1};
        int[] nums = {26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
//        int[] nums = {5, 2, 6, 1};
        t.countSmaller(nums);
//        System.out.println(Arrays.toString(t.indexArr));
        System.out.println(Arrays.toString(t.reverseCount));
    }

    /**
     * 下标数组，用于记录元素下标
     */
    int[] indexArr;
    /**
     * 辅助数组，不需要每次递归都新建一次，与传统归并不同，其中存放的是下标
     */
    int[] assist;
    /**
     * 记录逆序对个树数组，下标和原数组对应
     */
    int[] reverseCount;

    /**
     * 思路：借助归并排序，记下下标，一边排序一边统计逆序对
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return result;
        }

        indexArr = IntStream.rangeClosed(0, nums.length - 1).toArray();
        assist = new int[nums.length];
        reverseCount = new int[nums.length];
        int len = nums.length;
        for (int step = 1; step < len; step += step) {
            for (int i = 0; i < len - step; i += 2 * step) {
                doMergeAndCountReverse(nums, indexArr, i, i + step - 1, Math.min(len - 1, i + 2 * step - 1));
            }
        }
        for (int count : reverseCount) {
            result.add(count);
        }

        return result;
    }

    /**
     * 借助下标数组处理和统计逆序对，原因是在归并排序中，元素的位置已经改变
     * 一个元素在算法的执行过程中位置发生变化，我们还想定位它，
     * 这样的场景我们在 “最小索引堆” 中曾经学习过，从中得到启发，不妨也设置一个 “索引数组” 吧。
     *
     * @param nums     原数组，用于比较
     * @param indexArr 下标数组，归并排序实际操作的是下标数组
     */
    private void doMergeAndCountReverse(int[] nums, int[] indexArr, int low, int mid, int high) {
        System.arraycopy(indexArr, low, assist, low, high - low + 1);

        int left = low, right = mid + 1;
        int mergeResult = low;
        while (left <= mid && right <= high) {
            if (nums[assist[left]] <= nums[assist[right]]) {
                // 每次前置数组出列时，记录数量，数量为后置数组已经出列的个数
                // 例如前置：[2 5 6 7] 后置：[1 3 4 8]中2出列时，右侧只有1出列，数量为1
                reverseCount[assist[left]] += right - mid - 1;
                indexArr[mergeResult++] = assist[left++];
            } else {
                indexArr[mergeResult++] = assist[right++];
            }
        }

        while (left <= mid) {
            // 后置数组全出列时，前置数组的每一个都加上右侧数组长度，
            // 理由是前置中的任意未出列元素比后置所有元素都大
            // 如：[8 9 10 11] 和 [1 2 3 4]，左侧每个逆序对都是4
            reverseCount[assist[left]] += high - mid;
            indexArr[mergeResult++] = assist[left++];
        }

        while (right <= high) {
            indexArr[mergeResult++] = assist[right++];
        }

    }
}