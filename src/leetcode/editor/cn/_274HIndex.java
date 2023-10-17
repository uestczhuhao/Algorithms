package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

public class _274HIndex {
    public static void main(String[] args) {
        Solution t = new _274HIndex().new Solution();
        int[] citations = {1, 3, 1};
        System.out.println(t.hIndex(citations));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 模拟
        public int hIndex1(int[] citations) {
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

        /**
         * 根据题意可知，h指数的最大值不会超过n（因为只有n篇论文）
         * 因此可以考虑用计数排序的方式（时间复杂度O(n)），用 counter[i] 记录论文引用次数为i的论文有几篇（tips：引用次数大于n时，记作n，因为H不会大于n）
         * 然后从后往前遍历数组counter，同时累加论文的篇数（counter[i]的值），记作total
         * 当total >= counter[i] 时，即找到了 H （即此时的引用次数i）
         * <p>
         * 为啥要从后往前： counter[i] 记录的是论文引用次数为i的论文的论文篇数，由于H是找最大值，因此从n开始尝试找
         * 假设：count[n] = n >= n，说明引用次数大于等于n的论文有n篇，则H = n
         * 否则，不妨假设count[n] = 1 < n，说明引用次数大于等于n的论文有1篇，此时不能确定H的值（因为此时的1不一定是最大值）
         * 继续遍历n-1，此时至少被引用n-1次的论文数为count[n] + count[n-1]，需要比较该值与 n-1 的关系
         * 同理，若 count[n] + count[n-1] >= n-1，表示至少引用 n-1 次的论文篇数大于等于n-1。则H = n-1（此时H最大，依次类推）
         */
        public int hIndex2(int[] citations) {
            int paperNum = citations.length;
            // 记录论文引用次数为i的论文有几篇
            int[] counter = new int[paperNum + 1];
            for (int citation : citations) {
                if (citation >= paperNum) {
                    counter[paperNum]++;
                } else {
                    counter[citation]++;
                }
            }
            int total = 0;
            for (int i = paperNum; i >= 0; i--) {
                total += counter[i];
                if (total >= i) {
                    return i;
                }
            }
            return 0;
        }

        /**
         * 二分查找，H的值在[0, n] 之间，因此可以对0~n的范围做二分，对每个mid，遍历数组，查看mid是否能作为H（时间复杂度O(n*logn)）
         */
        public int hIndex(int[] citations) {
            int low = 0, high = citations.length;
            while (low < high) {
                // mid是当前循环的引用次数
                // 加偏移量，由于是 low = mid（不+1），当low + 1 = high时，如果mid不加偏移量，则mid = low，下一轮的low和high都不变，会进入死循环
                // 如果加了偏移量，当low + 1 = high时，mid = low+1 = high，此时会及时退出循环
                int mid = low + (high - low + 1) / 2;
                int pages = 0;
                for (int citation : citations) {
                    if (citation >= mid) {
                        pages++;
                    }
                }
                if (pages >= mid) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            return low;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
