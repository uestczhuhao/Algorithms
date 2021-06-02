package leetcode.editor.cn;

//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,2,3,2]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,1,0,1,99]
//输出：99
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -231 <= nums[i] <= 231 - 1 
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 
// 
//
// 
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
// Related Topics 位运算 
// 👍 615 👎 0


import java.util.HashMap;
import java.util.Map;

public class _137SingleNumberIi {
    public static void main(String[] args) {
        Solution t = new _137SingleNumberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNumber1(int[] nums) {
            int len = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> m : map.entrySet()) {
                if (m.getValue() == 1) {
                    return m.getKey();
                }
            }
            return -1;
        }

        public int singleNumber(int[] nums) {
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                int total = 0;
                for (int num : nums) {
                    // 取第i位，注意要 &1
                    total += ((num >> i) & 1);
                }

                // 此时ans的第i位为1，否则为0
                if (total % 3 != 0) {
                    ans |= (1 << i);
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}