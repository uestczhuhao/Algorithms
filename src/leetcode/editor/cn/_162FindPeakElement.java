package leetcode.editor.cn;

/**
 * <p>峰值元素是指其值严格大于左右相邻值的元素。</p>
 *
 * <p>给你一个整数数组&nbsp;<code>nums</code>，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 <strong>任何一个峰值</strong> 所在位置即可。</p>
 *
 * <p>你可以假设&nbsp;<code>nums[-1] = nums[n] = -∞</code> 。</p>
 *
 * <p>你必须实现时间复杂度为 <code>O(log n)</code><em> </em>的算法来解决此问题。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = <code>[1,2,3,1]</code>
 * <strong>输出：</strong>2
 * <strong>解释：</strong>3 是峰值元素，你的函数应该返回其索引 2。</pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = <code>[</code>1,2,1,3,5,6,4]
 * <strong>输出：</strong>1 或 5
 * <strong>解释：</strong>你的函数可以返回索引 1，其峰值元素为 2；
 * &nbsp;    或者返回索引 5， 其峰值元素为 6。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 1000</code></li>
 * <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
 * <li>对于所有有效的 <code>i</code> 都有 <code>nums[i] != nums[i + 1]</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 933</li><li>👎 0</li></div>
 */

public class _162FindPeakElement {
    public static void main(String[] args) {
        Solution t = new _162FindPeakElement().new Solution();
        int[] nums = {1};
        System.out.println(t.findPeakElement(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 在确保有解的前提下，当nums[mid] > nums[mid + 1]时，让high = mid，否则low = mid + 1
         * 其有两个前提：
         * 1. 对于任意数组而言，一定存在峰值（一定有解）；
         *     a. 数组长度为1，则解为0（num[-1] 和 nums[n] 值为负无穷）
         *     b. 数组长度大于1，如果 nums[0] > nums[1]，则解为0，否则，继续往后
         *        若nums[1] > nums[2]，则解为1（此时num[1] > nums[0] 必成立，前提数组相邻数字不相等），依次类推
         *        若持续到nums[n-1]，则数组单调递增，则n-1为解
         * 2. 二分不会错过峰值。
         *     a. 如果当前位置大于其左邻居（右邻居），那么在当前位置的右边（左边）必然存在峰值
         *        即nums[x] > nums[x-1] 时，其右边必有解（反之左边必有解），但左边不一定有解
         *
         */
        public int findPeakElement(int[] nums) {
            int low = 0, high = nums.length - 1;
            int mid;
            // 关于边界：mid 不能在low = high - 1时无限循环（即mid永远为low或high）
            // 当check(mid) == true，调整的是high时，mid = (low + high) >> 1（在上诉情况时取low）
            // 当check(mid) == true，调整的是low时，mid = (low + high + 1) >> 1（在上诉情况时取high）
            while (low < high) {
                mid = (low + high) >> 1;
                if (nums[mid] > nums[mid + 1]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            // 以下的解法也可以过
//            while (low < high) {
//                mid = (low + high + 1) >> 1;
//                if (nums[mid] > nums[mid - 1]) {
//                    low = mid;
//                } else {
//                    high = mid - 1;
//                }
//            }
            return low;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
