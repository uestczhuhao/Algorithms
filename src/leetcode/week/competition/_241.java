package leetcode.week.competition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mizhu
 * @date 2021/5/16 10:31
 */
public class _241 {
    public static void main(String[] args) {
        _241 t = new _241();
        int[] nums = {3, 4, 5, 6, 7, 8};
//        System.out.println(t.subsetXORSum(nums));
//        System.out.println(t.minSwaps("100"));
//        System.out.println(t.minSwaps("1110"));
//        System.out.println(t.minSwaps("0010111"));
//        System.out.println(t.minSwaps("00011110110110000000000110110101011101111011111101010010010000000000000001101101010010001011110000001101111111110000110101101101001011000011111011101101100110011111110001100110001110000000001100010111110100111001001111100001000110101111010011001"));
    }


    //TODO：剩余1题待研究

    /**
     * 思路：
     * 1 分摊时间复杂度，初始化时间复杂度为线性，避免O（n^2）
     * 2 把数组长度较长的num2储存起来，便于在O（1）的时间获取结果
     */
    class FindSumPairs {
        private int[] nums1;
        private int[] nums2;
        private Map<Integer, Integer> num2Map = new HashMap<>();

        public FindSumPairs(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            buildSumCountMap();
        }

        private void buildSumCountMap() {
            for (int j = 0; j < nums2.length; j++) {
                num2Map.put(nums2[j], num2Map.getOrDefault(nums2[j], 0) + 1);
            }
        }

        public void add(int index, int val) {
            num2Map.put(nums2[index], num2Map.getOrDefault(nums2[index], 0 ) - 1);
            nums2[index] += val;
            num2Map.put(nums2[index], num2Map.getOrDefault(nums2[index], 0 ) + 1);
        }

        /**
         * 把较长的num2放在map中，降低时间复杂度
         */
        public int count(int tot) {
            int ans = 0;
            for (int num : nums1) {
                if (num2Map.containsKey(tot - num)) {
                    ans += num2Map.get(tot - num);
                }
            }

            return ans;
        }
    }

    int answer = 0;

    public int subsetXORSum(int[] nums) {
        if (nums.length == 0) {
            return answer;
        }

        dfsAndXor(nums, 0, 0);
        return answer;
    }

    private void dfsAndXor(int[] nums, int start, int curXor) {
        answer += curXor;

        for (int i = start; i < nums.length; i++) {
            curXor ^= nums[i];
            dfsAndXor(nums, i + 1, curXor);
            curXor ^= nums[i];
        }
    }


    public int minSwaps(String s) {
        int len = s.length();
        int[] count = new int[2];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '0') {
                count[0]++;
            } else {
                count[1]++;
            }
        }

        if (Math.abs(count[0] - count[1]) > 1) {
            return -1;
        }

        // 0 和 1 一样多，则判断以0开头和以1开头的最小值
        if (len % 2 == 0) {
            return Math.min(diff(s, 0), diff(s, 1));
        } else if (count[0] > count[1]) {
            // 0多则直接返回0开头的交换次数
            return diff(s, 0);
        } else {
            // 1多则直接返回1开头的交换次数
            return diff(s, 1);
        }
    }

    private int diff(String s, int start) {
        char[] tgt = new char[s.length()];
        tgt[0] = (char) (start + '0');
        int dif = s.charAt(0) == tgt[0] ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            tgt[i] = (char) ('1' - tgt[i - 1] + '0');
            if (tgt[i] != s.charAt(i)) {
                dif++;
            }
        }
        return dif / 2;
    }


}
