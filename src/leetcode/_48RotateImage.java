package leetcode;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 2020/11/18 23:05
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class _48RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix.length != matrix[0].length) {
            return;
        }

        int size = matrix.length;
        int tmp;
        // 转置矩阵
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // 翻转列
        int half = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < half; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[i][size - 1 - j];
                matrix[i][size - 1 - j] = tmp;
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = new int[3][];
        int[] a = {1,2,3};
        int[] b = {4,5,6};
        int[] c = {7,8,9};
        matrix[0] = a;
        matrix[1] = b;
        matrix[2] = c;
        _48RotateImage t = new _48RotateImage();
        System.out.println(Arrays.deepToString(matrix));
        t.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));

    }
}
