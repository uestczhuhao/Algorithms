package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _271 {
    public static void main(String[] args) {
        _271 t = new _271();
        String rings = "B0B6G0R6R0R6G9G6";
//        System.out.println(t.countPoints(rings));
//        int[] nums = {1, 3, 3};
        int[] nums = {4, -2, -3, 4, 1};
//        System.out.println(t.subArrayRanges(nums));
        int[] plants = {2,2,3,3};
        System.out.println(t.minimumRefill(plants, 3, 4));
    }

    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int low = 0, high = plants.length - 1;
        int curCapA = capacityA, curCapB = capacityB;
        int ans = 0;
        while (low <= high) {
            if (low == high) {
                int curCap = Math.max(curCapA, curCapB);
                ans += curCap < plants[low] ? 1 : 0;
                break;
            }
            if (curCapA < plants[low]) {
                curCapA = capacityA;
                ans++;
            }
            curCapA -= plants[low];

            if (curCapB < plants[high]) {
                curCapB = capacityB;
                ans++;
            }
            curCapB -= plants[high];

            low++;
            high--;
        }

        return ans;
    }

    public long subArrayRanges(int[] nums) {
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int max = nums[i];
            int min = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(nums[j], max);
                min = Math.min(nums[j], min);
                if (max != min) {
                    ans += max - min;
                }
            }
        }
        return ans;
    }

    public int countPoints(String rings) {
        boolean[][] ringColor = new boolean[10][3];
        for (int i = 0; i < rings.length(); i += 2) {
            char color = rings.charAt(i);
            int location = rings.charAt(i + 1) - '0';
            if (color == 'R') {
                ringColor[location][0] = true;
            } else if (color == 'G') {
                ringColor[location][1] = true;
            } else {
                ringColor[location][2] = true;
            }
        }

        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += ringColor[i][0] && ringColor[i][1] && ringColor[i][2] ? 1 : 0;
        }
        return result;
    }
}
