package leetcode.editor.cn;

//在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。 
//
// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。 
//
// 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。 
//
// 说明: 
//
// 
// 如果题目有解，该答案即为唯一答案。 
// 输入数组均为非空数组，且长度相同。 
// 输入数组中的元素均为非负数。 
// 
//
// 示例 1: 
//
// 输入: 
//gas  = [1,2,3,4,5]
//cost = [3,4,5,1,2]
//
//输出: 3
//
//解释:
//从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
//开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
//开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
//开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
//开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
//开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
//因此，3 可为起始索引。 
//
// 示例 2: 
//
// 输入: 
//gas  = [2,3,4]
//cost = [3,4,3]
//
//输出: -1
//
//解释:
//你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
//我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
//开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
//开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
//你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
//因此，无论怎样，你都不可能绕环路行驶一周。 
// Related Topics 贪心算法 
// 👍 584 👎 0


public class _134GasStation {
    public static void main(String[] args) {
        Solution t = new _134GasStation().new Solution();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 4, 5, 1, 2};
        System.out.println(t.canCompleteCircuit(a, b));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：https://leetcode-cn.com/problems/gas-station/solution/jia-you-zhan-by-leetcode-solution/
         * 注意：暴力解法最多遍历数组两次，原因是一旦越过最后一个加油站回到第一个加油站
         * 如果当前位置不是解，那么就再也找不到解了，直接返回-1
         */
        public int canCompleteCircuit1(int[] gas, int[] cost) {
            if (gas == null || cost == null || gas.length != cost.length) {
                return -1;
            }
            int total = gas.length;
            int start = 0;

            while (start < total) {
                // 记录在这次寻找中的汽油和消耗总量
                int gasSum = 0;
                int costSum = 0;
                // 记录当前起始位置能到的加油站个数
                int num = 0;
                int curPos;
                while (num < total) {
                    curPos = (start + num) % total;
                    gasSum += gas[curPos];
                    costSum += cost[curPos];
                    // 表示当前起始位置转不了一圈
                    if (gasSum < costSum) {
                        break;
                    }
                    num ++;
                }

                // 如果当前位置能到达的加油站个数大于等于总数，则返回解
                // 否则，向前移动num + 1个加油站（理由是这段区间的所有加油站都不能转一圈）
                if (num >= total) {
                    return start;
                } else {
                    start = start + 1 + num;
                }
            }

            return -1;
        }

        /**
         * 思路：找到最小总汽油剩余最少的那个加油站
         * 在总体能跑完的情况（即总油量大于等于总消耗量）下，该加油站的下一个即为所求的起始加油站
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if (gas == null || cost == null || gas.length != cost.length) {
                return -1;
            }
            int length = gas.length;
            int totalGasSpare = 0;
            int minSpare = Integer.MAX_VALUE, endIndex = 0;
            for (int i = 0; i < length; i++) {
                totalGasSpare += gas[i] - cost[i];
                if (totalGasSpare < minSpare) {
                    minSpare = totalGasSpare;
                    endIndex = i;
                }
            }

            return totalGasSpare < 0 ? -1 : (endIndex + 1) % length;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}