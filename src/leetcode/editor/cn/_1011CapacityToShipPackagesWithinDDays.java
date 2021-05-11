package leetcode.editor.cn;

//传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。 
//
// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。 
//
// 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。 
//
// 
//
// 示例 1： 
//
// 
//输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//输出：15
//解释：
//船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//第 1 天：1, 2, 3, 4, 5
//第 2 天：6, 7
//第 3 天：8
//第 4 天：9
//第 5 天：10
//
//请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
//0) 是不允许的。 
// 
//
// 示例 2： 
//
// 
//输入：weights = [3,2,2,4,1,4], D = 3
//输出：6
//解释：
//船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
//第 1 天：3, 2
//第 2 天：2, 4
//第 3 天：1, 4
// 
//
// 示例 3： 
//
// 
//输入：weights = [1,2,3,1,1], D = 4
//输出：3
//解释：
//第 1 天：1
//第 2 天：2
//第 3 天：3
//第 4 天：1, 1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= D <= weights.length <= 5 * 104 
// 1 <= weights[i] <= 500 
// 
// Related Topics 数组 二分查找 
// 👍 355 👎 0


import java.util.Arrays;

public class _1011CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        Solution t = new _1011CapacityToShipPackagesWithinDDays().new Solution();
//        int[] w = {190, 417, 495, 463, 169, 450, 350, 477, 49, 45, 18, 439, 475, 313, 492, 400, 427, 439, 153, 158, 48, 430, 375, 352, 454, 391, 238, 95, 255, 170, 203, 49, 387, 483};
        int[] w = {1,2,3,1,1};
        System.out.println(t.shipWithinDays(w, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int shipWithinDays(int[] weights, int D) {
            int max = Arrays.stream(weights).sum();
            int min = Arrays.stream(weights).max().getAsInt();
            while (min < max) {
                int limit = (max - min) / 2 + min;
                if (check(weights, limit, D)) {
                    max = limit;
                } else {
                    min = limit + 1;
                }
            }
            return min;

        }

        /**
         * 由于需要按顺序装货，因此非组合，直接按顺序取值即可
         */
        private boolean check(int[] weights, int limit, int totalDays) {
            int days = 1;
            int curWeight = 0;
            for (int weight : weights) {
                if (curWeight + weight > limit) {
                    days++;
                    curWeight = 0;
                }
                curWeight += weight;

            }

            return days <= totalDays;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}