package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class _228SummaryRanges {
    public static void main(String[] args) {
        Solution t = new _228SummaryRanges().new Solution();
        System.out.println(t.summaryRanges(new int[] {0, 1, 2, 4, 5, 7}));
        System.out.println(t.summaryRanges(new int[] {0, 2, 3, 4, 6, 8, 9}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            int i = 0, start, end;
            List<String> ans = new ArrayList<>();
            while (i < nums.length) {
                start = i;
                end = i;
                while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) {
                    end = ++i;
                }
                // 注意：采用 nums[start] + "->" + nums[end] ，而不用sb的方式，会从0ms减速到7ms
                StringBuilder sb = new StringBuilder();
                sb.append(nums[start]);
                if (start != end) {
                    sb.append("->").append(nums[end]);
                }
                ans.add(sb.toString());
                i++;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
