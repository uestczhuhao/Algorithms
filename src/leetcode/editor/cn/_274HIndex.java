package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

public class _274HIndex {
    public static void main(String[] args) {
        Solution t = new _274HIndex().new Solution();
        int[] citations = {1};
        System.out.println(t.hIndex(citations));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            int ans = 0;
            for (int i = citations.length - 1; i >= 0; i--) {
                int num = citations.length - i;
                if (num > citations[i]) {
                    break;
                }
                ans = Math.max(ans, num);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
