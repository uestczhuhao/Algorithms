package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 2021/1/28 21:46
 */
public class _85MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int rowNum = matrix.length;
        int columnNum = matrix[0].length;
        int[][] continueNums = new int[rowNum][columnNum];
        for (int i = 0; i < rowNum; i++) {
            int num = 0;
            for (int j = 0; j < columnNum; j++) {
                if (matrix[i][j] == '1') {
                    num++;
                } else {
                    num = 0;
                }
                continueNums[i][j] = num;
            }
        }

        // 接下来是84题单调栈的解法了
        // 以列为维度，解最大矩形问题
        int maxArea = 0;
        for (int j = 0; j < columnNum; j++) {
            int[] left = new int[rowNum];
            int[] right = new int[rowNum];
            Arrays.fill(left, -1);
            Arrays.fill(right, rowNum);
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < rowNum; i++) {
                int current = continueNums[i][j];
                while (!stack.isEmpty() && continueNums[stack.peek()][j] >= current) {
                    right[stack.pop()] = i;
                }

                left[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < rowNum; i++) {
                maxArea = Math.max(maxArea, (right[i] - left[i] - 1) * continueNums[i][j]);
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = {
            {'0', '0', '0', '0', '0', '0', '1'},
            {'0', '0', '0', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1'},
            {'0', '0', '0', '1', '1', '1', '1'},
//            {'1', '0', '1', '0', '0'},
//            {'1', '0', '1', '1', '1'},
//            {'1', '1', '1', '1', '1'},
//            {'1', '0', '0', '1', '0'}
        };

        _85MaximalRectangle t = new _85MaximalRectangle();
        System.out.println(t.maximalRectangle(matrix));
    }
}
