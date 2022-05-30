package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>给你一个数组 <code>points</code> ，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示 <strong>X-Y</strong> 平面上的一个点。求最多有多少个点在同一条直线上。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/25/plane1.jpg" style="width: 300px; height: 294px;" />
 * <pre>
 * <strong>输入：</strong>points = [[1,1],[2,2],[3,3]]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/25/plane2.jpg" style="width: 300px; height: 294px;" />
 * <pre>
 * <strong>输入：</strong>points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= points.length <= 300</code></li>
 * <li><code>points[i].length == 2</code></li>
 * <li><code>-10<sup>4</sup> <= x<sub>i</sub>, y<sub>i</sub> <= 10<sup>4</sup></code></li>
 * <li><code>points</code> 中的所有点 <strong>互不相同</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>几何</li><li>数组</li><li>哈希表</li><li>数学</li></div></div><br><div><li>👍 414</li><li>👎 0</li></div>
 */

public class _149MaxPointsOnALine {
    public static void main(String[] args) {
        Solution t = new _149MaxPointsOnALine().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 暴力法：枚举任意两个点组成的直线，再看第3个点是否在其上
         * 最后取同一个直线上点最多的作为答案
         */
        public int maxPoints1(int[][] points) {
            if (points.length < 3) {
                return points.length;
            }
            int len = points.length;
            int ans = 0;
            for (int i = 0; i < len; i++) {
                int[] x = points[i];
                for (int j = i + 1; j < len; j++) {
                    int[] y = points[j];
                    int curPoints = 2;
                    for (int k = j + 1; k < len; k++) {
                        int[] z = points[k];
                        if ((y[1] - x[1]) * (z[0] - y[0]) == (z[1] - y[1]) * (y[0] - x[0])) {
                            curPoints++;
                        }
                    }
                    ans = Math.max(ans, curPoints);
                }
            }
            return ans;
        }

        /**
         * 对每一个i，遍历每个点j，保存其斜率至map，则map中最大的即为答案
         * 注意：求斜率时，当求出了deltaX和deltaY之后，不能直接相除，而是将其化简为最简的分式，以字符串的形式存入map
         * 如：deltaX和deltaY分别为2、4 和 4、8，前者在map中的key为1_2（2/2_4/2），后者的key也为1_2
         */
        public int maxPoints(int[][] points) {
            if (points.length < 3) {
                return points.length;
            }

            int len = points.length;
            int ans = 0;
            for (int  i = 0; i< len; i++) {
                Map<String, Integer> map = new HashMap<>();
                int max = 0;
                for (int j = i + 1; j < len; j++) {
                    int deltaX = points[i][0] - points[j][0];
                    int deltaY = points[i][1] - points[j][1];
                    int k = gcd(deltaX, deltaY);
                    String key = (deltaX / k) + "_" + (deltaY / k);
                    map.put(key, map.getOrDefault(key, 0 ) + 1);
                    max = Math.max(max, map.get(key));
                }
                ans = Math.max(ans, max + 1);
            }
            return ans;
        }

        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
