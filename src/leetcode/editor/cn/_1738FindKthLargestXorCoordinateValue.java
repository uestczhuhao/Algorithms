package leetcode.editor.cn;

//给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。 
//
// 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下
//标从 0 开始计数）执行异或运算得到。 
//
// 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[5,2],[1,6]], k = 1
//输出：7
//解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。 
//
// 示例 2： 
//
// 输入：matrix = [[5,2],[1,6]], k = 2
//输出：5
//解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。 
//
// 示例 3： 
//
// 输入：matrix = [[5,2],[1,6]], k = 3
//输出：4
//解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。 
//
// 示例 4： 
//
// 输入：matrix = [[5,2],[1,6]], k = 4
//输出：0
//解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 1000 
// 0 <= matrix[i][j] <= 106 
// 1 <= k <= m * n 
// 
// Related Topics 数组 
// 👍 38 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

public class _1738FindKthLargestXorCoordinateValue {
    public static void main(String[] args) {
        Solution t = new _1738FindKthLargestXorCoordinateValue().new Solution();
        int[][] matrix1 = {{5, 2}, {1, 6}};
        int[][] matrix = {{3,10,9,5,5,7},{0,1,7,3,8,1},{9,3,0,6,1,6},{10,2,9,10,10,7}};
        int k = 18;
        System.out.println(t.kthLargestValue(matrix, k));
//        System.out.println(t.kthLargestValue(matrix1, k));
//        System.out.println("结果");
//        System.out.println(1 ^ 10 ^ 2 ^ 3 ^ 4 ^ 9);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int kthLargestValue(int[][] matrix, int k) {
            int row = matrix.length;
            int col = matrix[0].length;
            // 存放每一行的前i项的异或和
            int[][] rowXor = new int[row][col];

            PriorityQueue<Integer> queue = new PriorityQueue<>(k);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    rowXor[i][j] = matrix[i][j] ^ (j == 0 ? 0 : rowXor[i][j - 1]);
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = col - 1; j >= 0; j--) {
                    rowXor[i][j] ^= (i == 0 ? 0 : rowXor[i - 1][j]);
                    if (queue.isEmpty() || queue.size() < k || rowXor[i][j] > queue.peek()) {
                        queue.offer(rowXor[i][j]);
                        while (queue.size() > k) {
                            queue.poll();
                        }
                    }
                }

            }
            return queue.poll();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}