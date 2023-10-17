package leetcode.editor.cn;

import java.util.Arrays;

public class _167TwoSumIiInputArrayIsSorted {
    public static void main(String[] args) {
        Solution t = new _167TwoSumIiInputArrayIsSorted().new Solution();
        int[] ns = {2, 7, 11, 15};
        System.out.println(Arrays.toString(t.twoSum(ns, 9)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int i = 0, j = numbers.length - 1;
            int[] ans = {i, j};
            while (i < j) {
                int s = numbers[i] + numbers[j];
                if (s == target) {
                    ans[0] = i + 1;
                    ans[1] = j + 1;
                    return ans;
                } else if (s > target) {
                    j--;
                } else {
                    i++;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
