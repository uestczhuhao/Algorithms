package leetcode;

/**
 * @author mizhu
 * @date 2020/11/14 20:40
 * <p>
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *  
 * <p>
 * 提示：
 * <p>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 */
public class _41FirstMissingPositive {
    /**
     * 思路：对于0-n 的数，尽量交换使得其出现在 i = num[i] - 1的地方（如：1 出现在第 0 项）
     * 完成后遍历数组，第一个不在其位置上的就是要找的数
     * 否则，返回n+1
     * 注意：while条件中，必须比较nums[nums[i] - 1] != nums[i]，而不是nums[i] - 1 != i
     * 原因是在[1, 1]的测试用例里，当i=1时，nums[i] - 1 != i，但nums[nums[i] - 1] == nums[i]，此时需要退出循环，否则会死循环
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int tmp;
        for (int i = 0; i < nums.length; i++) {
            /*
            如果 nums[nums[i] - 1] != nums[i] ，代表nums[i]已经出现在了应该出现的地方
            此时，num[i] - 1 = i
             */
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        _41FirstMissingPositive t = new _41FirstMissingPositive();
//        int[] nums = {7, 9, 10};
        int[] nums = {1, 1};
        System.out.println(t.firstMissingPositive(nums));
    }
}
