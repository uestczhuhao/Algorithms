package leetcode;

/**
 * @author mizhu
 * @date 20-1-19 下午9:23
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */
public class _287FindDuplicateNumber {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(findDuplicate(nums));
    }


    /**
     * 数组表示链表，找出链表环的起点，快指针从环起点开始
     */
    static public int findDuplicate(int[] nums) {
        if (null == nums || nums.length <= 0) {
            return -1;
        }

        // 第一步，通过快慢指针找出相遇的位置
        int slowPointer = nums[0];
        int fastPointer = nums[0];
        do {
            // 等效为链表走一步
            slowPointer = nums[slowPointer];
            // 等效为链表走两步
            fastPointer = nums[nums[fastPointer]];
        } while (slowPointer != fastPointer);

        // 第二步，一个从起点，一个从相遇点走，每次都走一步，再次相遇则为环的起点
        int startPosition = nums[0];
        int encounterPosition = slowPointer;
        while (encounterPosition != startPosition) {
            encounterPosition = nums[encounterPosition];
            startPosition = nums[startPosition];
        }

        return encounterPosition;
    }

    /**
     * 二分法，每次取mid，查看nums中小于等于mid的数count，
     * 如果count大于mid，则表示重复数字出现区间为[left, mid]，否则出现区间为[mid+1, right]
     */
    public int findDuplicate1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        // left < right 的写法在于每次剔除不符合条件的解，最后返回的是所求解
        while (left < right) {
            // mid向下取整，当right = left + 1时，下一次mid = left，保证能退出循环
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            // 当1～n中小于等于mid的数字大于mid时，代表重复数字一定出现在左半边
            // 例如1～8的数中，小于等于4的数大于4，一定有1～4中的某个数重复了
            if (count > mid) {
                // 下一个区间在[left, mid]
                right = mid;
            } else {
                // 下一个区间在[mid + 1, right]
                left = mid + 1;
            }
        }
        return left;
    }

}
