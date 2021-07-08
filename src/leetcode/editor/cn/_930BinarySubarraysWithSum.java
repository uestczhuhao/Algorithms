package leetcode.editor.cn;

//给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。 
//
// 子数组 是数组的一段连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,1,0,1], goal = 2
//输出：4
//解释：
//如下面黑体所示，有 4 个满足题目要求的子数组：
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0,0,0], goal = 0
//输出：15
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// nums[i] 不是 0 就是 1 
// 0 <= goal <= nums.length 
// 
// Related Topics 数组 哈希表 前缀和 滑动窗口 
// 👍 137 👎 0


import java.util.HashMap;
import java.util.Map;

public class _930BinarySubarraysWithSum {
    public static void main(String[] args) {
        Solution t = new _930BinarySubarraysWithSum().new Solution();
        int[] nums = {1, 0, 1, 0, 1};
        System.out.println(t.numSubarraysWithSum(nums, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * map + 前缀和，map存放sum和sum存在的次数
         * 遍历到i时，[0, i] 的前缀和 sum 和之前map中存在的前缀和做减法，若结果为goal，即为合法的解
         */
        public int numSubarraysWithSum1(int[] nums, int goal) {
            Map<Integer, Integer> map = new HashMap<>();
            int sum = 0;
            int ans = 0;
            map.put(0, 1);
            for (int num : nums) {
                sum += num;
                ans += map.getOrDefault(sum - goal, 0);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }

            return ans;
        }

        /**
         * 注意每个元素不是0就是1
         * 因此找到[left1, left2)区间，其中的每个元素都满足curSum == goal，ans += left2 - left1即可
         */
        public int numSubarraysWithSum(int[] nums, int goal) {
            int ans = 0;
            int curSum1 = 0, curSum2 = 0;
            int left1 = 0, left2 = 0, right = 0;
            while (right < nums.length) {
                curSum1 += nums[right];
                while (left1 <= right && curSum1 > goal) {
                    curSum1 -= nums[left1++];
                }
                curSum2 += nums[right];
                while (left2 <= right && curSum2 >= goal) {
                    curSum2 -= nums[left2 ++];
                }
                ans += left2 - left1;
                right++;
            }

            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}