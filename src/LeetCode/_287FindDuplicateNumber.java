package LeetCode;

/**
 * @author mizhu
 * @date 20-1-19 下午9:23
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist.
 *
 * Assume that there is only one duplicate number, find the duplicate one.
 *
 * note:
 * 1. You must not modify the array (assume the array is read only).
 * 2. You must use only constant, O(1) extra space.
 * 3. Your runtime complexity should be less than O(n2).
 * 4. There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class _287FindDuplicateNumber {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        System.out.println(findDuplicate(nums));
    }


    /**
     * 数组表示链表，找出链表环的起点，快指针从环起点开始，
     */
    static public int findDuplicate(int[] nums) {
        if (null == nums || nums.length <=0 ) {
            return -1;
        }

        // 第一步，通过快慢指针找出相遇的位置
        int slowPointer = nums[0];
        int fastPointer = nums[0];
        do{
            // 等效为链表走一步
            slowPointer = nums[slowPointer];
            // 等效为链表走两步
            fastPointer = nums[nums[fastPointer]];
        } while(slowPointer != fastPointer);

        // 第二步，一个从起点，一个从相遇点走，每次都走一步，再次相遇则为环的起点
        int startPosition = nums[0];
        int encounterPosition = slowPointer;
        while (encounterPosition != startPosition) {
            encounterPosition = nums[encounterPosition];
            startPosition = nums[startPosition];
        }

        return encounterPosition;
    }

}
