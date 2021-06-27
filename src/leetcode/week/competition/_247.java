package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mizhu
 * @date 2021/6/27 10:29
 */
public class _247 {
    public static void main(String[] args) {
        _247 t = new _247();
        int[] nums = {4, 2, 5, 9, 7, 4, 8};
        int[][] grid = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
//        System.out.println(t.maxProductDifference(nums));
//        System.out.println(Arrays.deepToString(t.rotateGrid(grid, 2)));
        System.out.println(t.wonderfulSubstrings("aba"));
    }

    long ans = 0;

    public long wonderfulSubstrings(String word) {
        long[][] dp = new long[word.length() + 1][word.length() + 1];
        return -1;
    }

    private void dfs(String word, int start, int[] curChNum, StringBuilder curSub) {
        int count = 0;
        if (start > word.length()) {
            return;
        }

        for (int i = 0; i < curChNum.length; i++) {
            if (curChNum[i] > 0 && curChNum[i] % 2 == 1) {
                count++;
            }

            if (count > 1) {
                break;
            }
        }

        ans += count > 1 ? 0 : 1;
        for (int i = start; i < word.length(); i++) {
            curChNum[word.charAt(i) - 'a']++;
            curSub.append(word.charAt(i));
            dfs(word, i + 1, curChNum, curSub);
            curSub.deleteCharAt(curSub.length() - 1);
            curChNum[word.charAt(i) - 'a']--;
        }
    }

    public int maxProductDifference(int[] nums) {
        int min = Integer.MAX_VALUE, min1 = min;
        int max = Integer.MIN_VALUE, max1 = max;
        for (int n : nums) {
            if (n < min1) {
                min1 = Math.max(min, n);
                min = Math.min(min, n);
            }
            if (n > max1) {
                max1 = Math.min(n, max);
                max = Math.max(n, max);
            }
        }

        return max * max1 - min * min1;
    }

    public int[][] rotateGrid(int[][] grid, int k) {
        int top = 0, bottom = grid.length - 1;
        int left = 0, right = grid[0].length - 1;
        while (top <= bottom && left <= right) {
            int total = 2 * (bottom - top + right - left);
            int offset = k % total;
            if (offset != 0) {
                int[] values = new int[total * 2];
                int index = 0;
                for (int i = right; i >= left; i--) {
                    values[index++] = grid[top][i];
                }
                for (int i = top + 1; i <= bottom; i++) {
                    values[index++] = grid[i][left];
                }
                for (int i = left + 1; i <= right; i++) {
                    values[index++] = grid[bottom][i];
                }
                for (int i = bottom - 1; i > top; i--) {
                    values[index++] = grid[i][right];
                }

                for (; index < values.length; index++) {
                    values[index] = values[index - total];
                }
                // 从新起点出发
                int start = total - offset;
                for (int i = right; i >= left; i--) {
                    grid[top][i] = values[start++];
                }
                for (int i = top + 1; i <= bottom; i++) {
                    grid[i][left] = values[start++];
                }
                for (int i = left + 1; i <= right; i++) {
                    grid[bottom][i] = values[start++];
                }
                for (int i = bottom - 1; i > top; i--) {
                    grid[i][right] = values[start++];
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }

        return grid;
    }


}
