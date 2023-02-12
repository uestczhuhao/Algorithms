package leetcode.week.competition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _332 {
    public static void main(String[] args) {
        _332 t = new _332();
        int[] nums = {5, 14, 13, 8, 12};
        int[] nums1 = {5};
//        System.out.println(t.findTheArrayConcVal(nums));
//        System.out.println(t.findTheArrayConcVal(nums1));


        String s = "1";
        int[][] queries = {{4, 5}};
//        System.out.println(Arrays.deepToString(t.substringXorQueries(s, queries)));
//        System.out.println(8 ^ 8);
//        System.out.println(Integer.parseInt("1111", 2));
//        System.out.println(Integer.parseInt("11111", 2));
        int[] nums2 = {0, 1, 7, 4, 4, 5};
        int[] nums3 = {0, 2, 2};
        double d1 = Math.pow(2, 30);
        double d2 = Math.pow(10, 9);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d1 / d2);
//        System.out.println(t.lowBound(nums3, 0, -1));
//        System.out.println(t.countFairPairs(nums2, 3, 6));
    }

    public int[][] substringXorQueries(String s, int[][] queries) {
        Map<Integer, int[]> xOrSubMap = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // 预处理 30 位的原因是数据量最大是 10^9，且异或值的长度不超过两个运算数的长度，也就是说答案的长度不超过30位二进制数
            for (int j = i, pre = 0; j < Math.min(i + 30, n); j++) {
                int xor = pre * 2 + (s.charAt(j) - '0');
                pre = xor;
                if (!xOrSubMap.containsKey(xor)) {
                    xOrSubMap.put(xor, new int[] {i, j});
                } else {
                    int oldLen = xOrSubMap.get(xor)[1] - xOrSubMap.get(xor)[0];
                    if (oldLen > j - i) {
                        xOrSubMap.put(xor, new int[] {i, j});
                    }
                }
            }
        }
        int[][] ans = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = xOrSubMap.getOrDefault(queries[i][0] ^ queries[i][1], new int[] {-1, -1});
        }
        return ans;
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        long result = 0;
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n - 1; i++) {
            // 找到满足 nums[i] + nums[j] >= lower 的最小的j
            int left = lowBound(nums, i + 1, lower - nums[i]);
            // 如果找到的j不满足nums[i] + nums[j] >= lower（即不满足 nums[left] >= target）
            // 则表示target值过大，在[i+1, n-1]中所有值都比它小，则返回-1（找上边界时同理）
            if (left == -1) {
                continue;
            }

            int right = highBound(nums, i + 1, upper - nums[i]);
            if (right == -1) {
                continue;
            }

            // 最后找到的合法左右边界应该满足以下条件：
            // nums[left] >= lower - nums[i] && nums[right] <= upper - nums[i]
            result += right - left + 1;
        }

        return result;
    }


    // 寻找nums中第一个大于等于 target 值的数组下标
    private int lowBound(int[] nums, int start, int target) {
        int end = nums.length - 1;
        while (start < end) {
            int mid = (end + start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                // 该函数为找到左边界，因此当nums[mid] == target时，要在左边寻找，试图找到target的第一次出现位置
                // 下一轮搜索区间是[left, mid]
                // 与nums[mid] > target分支合并
                end = mid;
            }
        }

        return nums[start] >= target ? start : -1;
    }

    // 寻找nums中第一个小于于等于 target 值的数组下标
    private int highBound(int[] nums, int start, int target) {
        int end = nums.length - 1;
        while (start < end) {
            // 向上取整，避免死循环，例如left = 2，right = 3，且nums[left] == nums[right] == target时
            // 向上取证保证mid = 3，下一次退出，否则mid = 2，死循环
            int mid = (end + start + 1) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }

        return nums[start] <= target ? start : -1;
    }

    // 寻找nums中比第一个小于等于 target 值的数组下标
    public long findTheArrayConcVal(int[] nums) {
        long ans = 0L;
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int multi = (int) Math.log10(nums[high]) + 1;
            ans += Math.pow(10.0, multi) * nums[low] + nums[high];
            low++;
            high--;
        }
        if (low == high) {
            ans += nums[low];
        }
        return ans;
    }
}
