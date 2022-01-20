package leetcode.editor.cn;

//给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i
//- j) <= k 。如果存在，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,1], k = 3
//输出：true
//
// 示例 2：
//
//
//输入：nums = [1,0,1,1], k = 1
//输出：true
//
// 示例 3：
//
//
//输入：nums = [1,2,3,1,2,3], k = 2
//输出：false
//
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
// 0 <= k <= 10⁵
//
// Related Topics 数组 哈希表 滑动窗口 👍 419 👎 0


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _219ContainsDuplicateIi {
    public static void main(String[] args) {
        Solution t = new _219ContainsDuplicateIi().new Solution();
        int[] a = {99, 99};
        System.out.println(t.containsNearbyDuplicate(a, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean containsNearbyDuplicate1(int[] nums, int k) {
            Map<Integer, Integer> valueMaxIndexMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (valueMaxIndexMap.containsKey(nums[i])) {
                    int preIndex = valueMaxIndexMap.get(nums[i]);
                    if (Math.abs(preIndex - i) <= k) {
                        return true;
                    }
                }
                valueMaxIndexMap.put(nums[i], i);
            }
            return false;
        }

        /**
         * 滑动窗口解法，滑动窗口长度为k+1
         */
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (i > k) {
                    set.remove(nums[i - k - 1]);
                }
                if (set.contains(nums[i])) {
                    return true;
                }

                set.add(nums[i]);
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
