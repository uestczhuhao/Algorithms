package leetcode.editor.cn;

//给定一个增序排列数组 nums ，你需要在 原地 删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。 
//
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。 
//
// 
//
// 说明： 
//
// 为什么返回数值是整数，但输出的答案是数组呢？ 
//
// 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。 
//
// 你可以想象内部操作如下： 
//
// 
//// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//int len = removeDuplicates(nums);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//} 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,2,2,3]
//输出：5, nums = [1,1,2,2,3]
//解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 你不需要考虑数组中超出新长度后面的元素。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,1,1,1,1,2,3,3]
//输出：7, nums = [0,0,1,1,2,3,3]
//解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 你不需要考虑数组中超出新长度后面
//的元素。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3 * 104 
// -104 <= nums[i] <= 104 
// nums 按递增顺序排列 
// 
// Related Topics 数组 双指针 
// 👍 355 👎 0


import java.util.Arrays;

public class _80RemoveDuplicatesFromSortedArrayIi {
    public static void main(String[] args) {
        Solution t = new _80RemoveDuplicatesFromSortedArrayIi().new Solution();
        int[] nums = {1, 1, 2, 2, 2, 3};
        System.out.println(t.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：快慢指针，快指针指向需要移动的元素
         * 慢指针指向该元素实际移动到的位置
         * 使用count记录当前元素出现的次数，当count > 2 时移动元素
         */
        public int removeDuplicates(int[] nums) {
            if (nums == null) {
                return 0;
            }

            if (nums.length <= 2) {
                return nums.length;
            }


            int count = 1;
            // 参考：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/solution/shuang-zhi-zhen-zhi-tong-xiang-zhi-zhen-che-di-jie/
            // 第一个元素不可能重复，因此slow = 0
            int slow = 0, fast = 1;
            for (; fast < nums.length; fast++) {
                if (nums[fast] != nums[fast - 1]) {
                    count = 1;
                } else {
                    count++;
                }

                // 转变思路，在需要时才移动，并且把慢指针+1
                if (count <= 2) {
                    // 当有元素要移动过来时，合法区域此时会扩展，因此先 +1，再赋值
                    slow ++;
                    nums[slow] = nums[fast];
                }
            }

            return slow + 1;
        }

        public int removeDuplicates1(int[] nums) {
            int k = 2;
            if (nums == null) return 0;
            if (nums.length <= k) return nums.length;

            // 1.定义[0,index] 是修改后的满足要求的数组区间,这里已经把0 1 2 ...k- 1 ,共k个数 放进去了
            int index = k - 1;
            // 2.判断终止条件
            for (int i = k; i < nums.length; i++) {
                // 3.指针移动条件
                if (nums[i] != nums[index - k + 1]) {
                    index++;
                    nums[index] = nums[i];
                }
            }
            // 4.判断返回值
            return index + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}