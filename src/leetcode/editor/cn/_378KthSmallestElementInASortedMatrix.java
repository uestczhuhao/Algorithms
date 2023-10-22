package leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _378KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {
        Solution t = new _378KthSmallestElementInASortedMatrix().new Solution();
        int[][] m = {{1,5,9},{10,11,13},{12,13,15}};
        System.out.println(t.kthSmallest(m, 8));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 二分法，在最小值matrix[0][0]和最大值matrix[n-1][n-1]之间做二分，假设取中间值mid
         * 可以观察到小于等于mid的值都在其左上方，因此可以考虑从左下角开始寻找
         * 当matrix[i][j]小于等于mid，则可以判定出当前列中，i及以上的位置都小于等于mid，可以累加 i+1，同时向右移动
         * 当matrix[i][j]大于mid，此时还没找到当前列中第一个满足条件的值，继续往上走（不累加）
         */
        int[][] mat;

        public int kthSmallest(int[][] matrix, int k) {
            mat = matrix;
            int n = matrix.length;
            int low = matrix[0][0], high = matrix[n - 1][n - 1];
            // 一定有答案，取low<high，退出时low==high，即为答案
            while (low < high) {
                int mid = low + ((high - low) >> 1);
                if (check(mid, k)) {
                    high = mid;
                } else {
                    // 记住：low = mid + 1时，mid往下取
                    // high = mid - 1时，mid往上取
                    low = mid + 1;
                }
            }
            return low;
        }

        private boolean check(int target, int num) {
            int ans = 0, i = mat.length - 1, j = 0;
            while (i >= 0 && j < mat.length) {
                if (mat[i][j] <= target) {
                    ans += i + 1;
                    j++;
                } else {
                    i--;
                }
            }
            return ans >= num;
        }

        /**
         * 多路归并，优先队列中存放matrix的下标，按照在matrix中的元素大小排序
         * 当弹出当前(i, j)值时，把比它大的一个数入队列（右边或边的元素，取决于初始化时放入队列的是第一行还是第一列）
         * 时间复杂度：O(k*logn)，归并 k 次，每次堆中插入和弹出的操作时间复杂度均为logn，但k的最坏值为n^2，所以时间复杂度最坏为 O(n^2*logn)
         */
        public int kthSmallest1(int[][] matrix, int k) {
            int n = matrix.length;
            // 优先队列，存放下标，按照该下标对应的值在matrix中的值正序排列
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> matrix[a[0]][a[1]]));
            for (int i = 0; i < Math.min(k, n); i++) {
                queue.offer(new int[] {i, 0});
            }
            int cnt = 0, ans = matrix[0][0];
            while (cnt < k) {
                cnt++;
                int[] cur = queue.poll();
                int row = cur[0];
                int col = cur[1];
                ans = matrix[row][col];
                if (col < n - 1) {
                    queue.offer(new int[] {row, col + 1});
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
