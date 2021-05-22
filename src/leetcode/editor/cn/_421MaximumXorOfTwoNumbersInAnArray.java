package leetcode.editor.cn;

//给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。 
//
// 进阶：你可以在 O(n) 的时间解决这个问题吗？ 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：nums = [3,10,5,25,2,8]
//输出：28
//解释：最大运算结果是 5 XOR 25 = 28. 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [2,4]
//输出：6
// 
//
// 示例 4： 
//
// 
//输入：nums = [8,10,2]
//输出：10
// 
//
// 示例 5： 
//
// 
//输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
//输出：127
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// 0 <= nums[i] <= 231 - 1 
// 
// 
// 
// Related Topics 位运算 字典树 
// 👍 270 👎 0


import java.util.HashSet;
import java.util.Set;

public class _421MaximumXorOfTwoNumbersInAnArray {
    public static void main(String[] args) {
        Solution t = new _421MaximumXorOfTwoNumbersInAnArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMaximumXOR1(int[] nums) {
            int max = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    max = Math.max(max, nums[i] ^ nums[j]);
                }
            }

            return max;

        }

        /**
         * 哈希表，假设最大值位x，从高高低判断其每一位是否能取到1
         */
        public int findMaximumXOR(int[] nums) {
            int preX = 0;
            for (int i = 30; i >= 0; i--) {
                // 存放右移i位的preJ
                Set<Integer> curPreJ = new HashSet<>();
                for (int num : nums) {
                    curPreJ.add(num >> i);
                }
                // 当前位设置为1
                preX = (preX << 1) + 1;
                boolean found = false;
                for (int num : nums) {
                    if (curPreJ.contains(preX ^ (num >> i))) {
                        found = true;
                        break;
                    }
                }

                // 若当前位取不到1，则取0
                if (!found) {
                    preX -= 1;
                }
            }
            return preX;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}