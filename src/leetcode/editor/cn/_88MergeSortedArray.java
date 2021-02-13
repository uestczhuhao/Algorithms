package leetcode.editor.cn;

//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nu
//ms2 的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -109 <= nums1[i], nums2[i] <= 109 
// 
// Related Topics 数组 双指针 
// 👍 759 👎 0


public class _88MergeSortedArray {
    public static void main(String[] args) {
        Solution t = new _88MergeSortedArray().new Solution();
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int[] num2 = {2, 5, 6};
        int m = 3, n = 3;
        t.merge(num1, m, num2, n);
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (n <= 0) {
                return;
            }

            int mergedIndex = m + n - 1;
            while (mergedIndex >= 0) {
                int left = m <= 0 ? Integer.MIN_VALUE : nums1[m - 1];
                int right = n <= 0 ? Integer.MIN_VALUE : nums2[n - 1];
                if (left > right) {
                    nums1[mergedIndex] = left;
                    m--;
                } else {
                    nums1[mergedIndex] = right;
                    n--;
                }

                mergedIndex--;
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}