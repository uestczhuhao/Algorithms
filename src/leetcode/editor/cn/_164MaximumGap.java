package leetcode.editor.cn;

import java.util.Arrays;

public class _164MaximumGap {
    public static void main(String[] args) {
        Solution t = new _164MaximumGap().new Solution();
        int[] nums = {3, 6, 9, 1};
        _164MaximumGap[] a = new _164MaximumGap[10];
        Integer[] a1 = new Integer[10];
        System.out.println(t.maximumGap(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 基数排序，每一位数字个位大小排序，再按十位大小排序，依次类推，直到最高位
         */
        public int maximumGap1(int[] nums) {
            if (nums.length < 2) {
                return 0;
            }
            int n = nums.length;
            int[][] buf = new int[10][n];
            // nums的最大值为10^9，因此exp可能为10^10（最后乘以10），超过int最大值（稍大于2*10^9）
            long exp = 1;
            int max = Arrays.stream(nums).max().getAsInt();
            while (max >= exp) {
                // 存放当前位（比如各位、十位、百位等）
                int[] cnt = new int[10];
                for (int num : nums) {
                    int digit = num / (int) exp % 10;
                    buf[digit][cnt[digit]] = num;
                    cnt[digit]++;
                }
                int index = 0;
                // 把本轮收集到buf中的，按位排好序的数，再放回到num中去
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < cnt[i]; j++) {
                        nums[index++] = buf[i][j];
                    }
                }
                exp *= 10;
            }

            int ans = 0;
            for (int i = 1; i < n; i++) {
                ans = Math.max(ans, nums[i] - nums[i - 1]);
            }
            return ans;

        }

        /**
         * 桶排序，其中桶大小的选取和桶个数的确定和证明参考：https://leetcode.cn/problems/maximum-gap/solutions/498428/zui-da-jian-ju-by-leetcode-solution/
         * 相邻数字的最大间距不会小于 ceil((max - min) / (n-1))，其中n位数组的长度，原因是弱（平均分配时取等号，否则肯定大于）
         * 可取flow((max - min) / (n-1)) <= ceil((max - min) / (n-1))为桶的长度，那么最大间距肯定不在桶内部
         * 则取相邻桶中的最大/最小值的差，取差的最大值则为答案
         * 注意：一般的桶排序，桶的长度和个数的选取不一定
         */
        public int maximumGap(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return 0;
            }
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int num : nums) {
                max = Math.max(num, max);
                min = Math.min(num, min);
            }
            // 长度至少为1
            int bucketLen = Math.max(1, (max - min) / (n - 1));
            // 桶是数量是max - min的长度除以单位长度，由于向下取整，因此+1（考虑7/3，此时2个桶不够，需要3个）
            // 当刚好是整数时，存在一个空桶，对结果无影响
            int bucketNum = (max - min) / bucketLen + 1;
            // 桶中保留最大、最小值，便于相邻的桶中的比较；都为-1则表示空桶
            int[][] bucket = new int[bucketNum][2];
            for (int i = 0; i < bucketNum; i++) {
                Arrays.fill(bucket[i], -1);
            }

            for (int num : nums) {
                int pos = (num - min) / bucketLen;
                if (bucket[pos][0] == -1) {
                    bucket[pos][0] = bucket[pos][1] = num;
                } else {
                    bucket[pos][0] = Math.min(bucket[pos][0], num);
                    bucket[pos][1] = Math.max(bucket[pos][1], num);
                }
            }
            int ans = 0;
            int pre = -1;
            for (int i = 0; i < bucketNum; i++) {
                if (bucket[i][0] == -1) {
                    continue;
                }
                if (pre != -1) {
                    ans = Math.max(ans, bucket[i][0] - bucket[pre][1]);
                }
                pre = i;
            }
            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
