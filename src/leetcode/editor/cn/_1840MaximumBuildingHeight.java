package leetcode.editor.cn;

//在一座城市里，你需要建 n 栋新的建筑。这些新的建筑会从 1 到 n 编号排成一列。 
//
// 这座城市对这些新建筑有一些规定： 
//
// 
// 每栋建筑的高度必须是一个非负整数。 
// 第一栋建筑的高度 必须 是 0 。 
// 任意两栋相邻建筑的高度差 不能超过 1 。 
// 
//
// 除此以外，某些建筑还有额外的最高高度限制。这些限制会以二维整数数组 restrictions 的形式给出，其中 restrictions[i] = [id
//i, maxHeighti] ，表示建筑 idi 的高度 不能超过 maxHeighti 。 
//
// 题目保证每栋建筑在 restrictions 中 至多出现一次 ，同时建筑 1 不会 出现在 restrictions 中。 
//
// 请你返回 最高 建筑能达到的 最高高度 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 5, restrictions = [[2,1],[4,1]]
//输出：2
//解释：上图中的绿色区域为每栋建筑被允许的最高高度。
//我们可以使建筑高度分别为 [0,1,2,1,2] ，最高建筑的高度为 2 。 
//
// 示例 2： 
//
// 
//输入：n = 6, restrictions = []
//输出：5
//解释：上图中的绿色区域为每栋建筑被允许的最高高度。
//我们可以使建筑高度分别为 [0,1,2,3,4,5] ，最高建筑的高度为 5 。
// 
//
// 示例 3： 
//
// 
//输入：n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
//输出：5
//解释：上图中的绿色区域为每栋建筑被允许的最高高度。
//我们可以使建筑高度分别为 [0,1,2,3,3,4,4,5,4,3] ，最高建筑的高度为 5 。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= n <= 109 
// 0 <= restrictions.length <= min(n - 1, 105) 
// 2 <= idi <= n 
// idi 是 唯一的 。 
// 0 <= maxHeighti <= 109 
// 
// Related Topics 贪心算法 二分查找 
// 👍 14 👎 0


import java.util.Arrays;
import java.util.Comparator;

public class _1840MaximumBuildingHeight {
    public static void main(String[] args) {
        Solution t = new _1840MaximumBuildingHeight().new Solution();
        int[][] res = {{2,1}, {4,1}};
        System.out.println(t.maxBuilding(5, res));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * https://leetcode-cn.com/problems/maximum-building-height/solution/zui-gao-jian-zhu-gao-du-by-leetcode-solu-axbb/
         */
        public int maxBuilding(int n, int[][] restrictions) {
            int[][] orderRestrict = new int[restrictions.length + 2][2];
            int len = orderRestrict.length;
            orderRestrict[0] = new int[]{1, 0};
            Arrays.sort(restrictions, Comparator.comparingInt(can -> can[0]));
            System.arraycopy(restrictions, 0, orderRestrict, 1, restrictions.length);
            orderRestrict[len - 1] = new int[]{n, n - 1};

            // 从左到右的高度限制
            // 对(i, hi)和(j, hj)，i处的限制传递到j处，j处的限制为hj和(hi + (j-i))的较小值
            int left = 0, right = 0;
            for (int i = 1; i < len; i++) {
                left = orderRestrict[i - 1][0];
                right = orderRestrict[i][0];
                orderRestrict[i][1] = Math.min(orderRestrict[i][1], orderRestrict[i - 1][1] + (right - left));
            }

            // 从右到左的高度限制
            for (int j = len - 2; j >= 0; j--) {
                left = orderRestrict[j][0];
                right = orderRestrict[j + 1][0];
                orderRestrict[j][1] = Math.min(orderRestrict[j][1], orderRestrict[j + 1][1] + (right - left));
            }

            int maxHeight = 0;
            for (int i = 1; i < len; i++) {
                left = orderRestrict[i - 1][0];
                right = orderRestrict[i][0];
                // 计算 r[i][0] 和 r[i][1] 之间的建筑的最大高度
                maxHeight = Math.max(maxHeight, ((right - left) + orderRestrict[i][1] + orderRestrict[i-1][1]) / 2);
            }

            return maxHeight;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}