package leetcode.editor.cn;

public class _278FirstBadVersion {
    public static void main(String[] args) {
        Solution t = new _278FirstBadVersion().new Solution();
        System.out.println(t.firstBadVersion(1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

    public class Solution {
        /**
         * 二分，记住一个原则，当high=mid时，向下取整；low=mid时，向上取整。
         */
        public int firstBadVersion(int n) {
            int low = 1, high = n;
            while (low < high) {
                // 当isBadVersion(mid)时，由于要找到第一个，因此需要在[low, mid]中找，
                // 此时high = mid，因此应该向下取整（若向上取整，当low+1=high时，永远取high）
                int mid = low + ((high - low) >> 1);
                if (isBadVersion(mid)) {
                    high = mid;
                } else {
                    low = mid + 1;
                }

            }
            return low;
        }

        private boolean isBadVersion(int mid) {
            return mid >= 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
