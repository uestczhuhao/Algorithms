package leetcode;

import java.util.Arrays;

public class _16_3SumClosest {
    public int threeSumClosest(int[] nums, int target) {

        if (nums.length < 3) {
            throw new IllegalArgumentException("Nums length not enough");
        }
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2], res = sum;
        int min = Math.abs(sum - target);
        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                sum = nums[low] + nums[high] + nums[i];
                if (Math.abs(sum - target) < min) {
                    res = sum;
                }
                min = Math.min(min, Math.abs(sum - target));
                if (sum > target) {
                    high--;
                } else {
                    low++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _16_3SumClosest t = new _16_3SumClosest();
        System.out.println(t.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
