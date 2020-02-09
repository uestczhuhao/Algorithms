package LeetCode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author mizhu
 * @date 20-2-3 下午8:19
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *  
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 */
public class _239SlidingWindowMaximum {
    public static void main(String[] args) {
        _239SlidingWindowMaximum t = new _239SlidingWindowMaximum();
        int[] nums = {1,3,1,2,0,5};

        int k = 3;
        System.out.println(Arrays.toString(t.maxSlidingWindow(nums, k)));
    }

    private Deque<Integer> maxIndexDeque = new ArrayDeque<Integer>();

    /**
     * 借助双向队列，在遍历num每个元素时，处理双向链表
     * 1. 把头部越界，即超出窗口的元素剔除
     * 2. 从后往前剔除队列中比当前值小的元素，把当前值加入队列尾部，理由是在合法的元素范围，index前面的值比它先移走，所以index前面的更小值没有机会最大
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k == 1) {
            return nums;
        }

        int maxIndex = 0;
        for (int i = 0; i < k; i++) {
            addAndCleanDeque(nums, i, k);
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int[] result = new int[nums.length - k + 1];
        result[0] = nums[maxIndex];
        for (int i = k; i < nums.length; i++) {
            addAndCleanDeque(nums, i, k);
            result[i - k + 1] = nums[maxIndexDeque.getFirst()];
        }

        return result;
    }

    private void addAndCleanDeque(int[] nums, int index, int k) {
        // 删除下标越界的元素
        while (!maxIndexDeque.isEmpty() && maxIndexDeque.getFirst() <= index-k) {
            maxIndexDeque.removeFirst();
        }

        /*
        新加的元素，从后往前剔除比它小的，
        因为在合法的元素范围，index前面的值比它先移走，所以index前面的更小值没有机会最大
        但是其后的元素可能是某个时期的最大值，如nums[index] = 3（k=3），其后来了3个0，0比3小，但当3移走时0为当前最大值
        */
        while (!maxIndexDeque.isEmpty() && nums[maxIndexDeque.getLast()] < nums[index]) {
            maxIndexDeque.removeLast();
        }
        maxIndexDeque.addLast(index);
    }


}
