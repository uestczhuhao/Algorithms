package leetcode.week.competition;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _255 {
    public static void main(String[] args) {
        _255 t = new _255();
        int[] nums = {3, 3};
//        System.out.println(t.findGCD(nums));
        String[] nums1 = {"001", "000", "000"};
//        System.out.println(t.findDifferentBinaryString(nums1));
//        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        1 1 3
        int[][] mat = {{10, 3, 7, 7, 9, 6, 9, 8, 9, 5}, {1, 1, 6, 8, 6, 7, 7, 9, 3, 9}, {3, 4, 4, 1, 3, 6, 3, 3, 9, 9}, {6, 9, 9, 3, 8, 7, 9, 6, 10, 6}};
        System.out.println(t.minimizeTheDifference(mat, 5));
    }

    int ans = Integer.MAX_VALUE;
    int row, col;

    /**
     * dfs解法，超时了
     */
    public int minimizeTheDifference(int[][] mat, int target) {
        row = mat.length;
        col = mat[0].length;
        dfs(0, 0, mat, target);
        return ans;
    }

    private void dfs(int startRow, int curSum, int[][] mat, int target) {
        if (startRow == row) {
            ans = Math.min(ans, Math.abs(curSum - target));
            return;
        }

        for (int j = 0; j < col; j++) {
            dfs(startRow + 1, curSum + mat[startRow][j], mat, target);
        }
    }

    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        int[] numValues = new int[n];
        Set<Integer> set = new HashSet<>();
        int index = 0;
        for (int i = 0; i < n; i++) {
            int curValue = Integer.parseUnsignedInt(nums[i], 2);
            if (!set.contains(curValue)) {
                numValues[index++] = curValue;
            }
            set.add(curValue);
        }
        int[] trimNumValues = Arrays.copyOf(numValues, index);
        Arrays.sort(trimNumValues);
        for (int i = 0; i < Math.pow(2, n); i++) {
            if (i >= trimNumValues.length || i != trimNumValues[i]) {
                return parseInt2NLenString(i, n);
            }
        }
        return parseInt2NLenString(0, n);
    }

    private String parseInt2NLenString(int src, int n) {
        String s = Integer.toBinaryString(src);
        StringBuilder sb = new StringBuilder();
        if (s.length() < n) {
            int index = n - s.length();
            while (index > 0) {
                sb.append('0');
                index--;
            }
        }
        return sb + s;
    }

    public int findGCD(int[] nums) {
        int max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        return doFindGCD(min, max);
    }

    private int doFindGCD(int low, int high) {
        if (low > high) {
            int tmp = low;
            low = high;
            high = tmp;
        }

        if (high % low == 0) {
            return low;
        } else {
            return doFindGCD(low, high % low);
        }
    }
}
