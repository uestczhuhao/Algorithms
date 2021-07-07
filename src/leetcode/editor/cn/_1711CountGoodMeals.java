package leetcode.editor.cn;

//大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。 
//
// 你可以搭配 任意 两道餐品做一顿大餐。 
//
// 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大
//餐 的数量。结果需要对 109 + 7 取余。 
//
// 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。 
//
// 
//
// 示例 1： 
//
// 
//输入：deliciousness = [1,3,5,7,9]
//输出：4
//解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
//它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
// 
//
// 示例 2： 
//
// 
//输入：deliciousness = [1,1,1,3,3,3,7]
//输出：15
//解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。 
//
// 
//
// 提示： 
//
// 
// 1 <= deliciousness.length <= 105 
// 0 <= deliciousness[i] <= 220 
// 
// Related Topics 数组 哈希表 
// 👍 91 👎 0


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class _1711CountGoodMeals {
    public static void main(String[] args) {
        Solution t = new _1711CountGoodMeals().new Solution();
        int[] delis = {1, 1, 1, 3, 3, 3, 7};
        System.out.println(t.countPairs(delis));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * hash表存储到当前为止的元素和其出现的次数
         * 当遍历到i时，其前面的 元素->出现次数 已经都在map中了
         * 用i和其前面的元素组合成2的次方，并不会重复
         */
        public int countPairs(int[] deliciousness) {
            Map<Integer, Integer> freqMap = new HashMap<>();
            int max = deliciousness[0];
            for (int d : deliciousness) {
                max = Math.max(max, d);
            }

            max += max;
            List<Integer> power = new LinkedList<>();
            for (int i = 0; Math.pow(2, i) <= max; i++) {
                power.add((int) Math.pow(2, i));
            }
            Collections.reverse(power);

            long ans = 0;
            for (int cur : deliciousness) {
                for (int pow : power) {
                    if (pow < cur) {
                        break;
                    }
                    ans = (ans + freqMap.getOrDefault(pow - cur, 0)) % 1000_000_007;
                }

                freqMap.put(cur, freqMap.getOrDefault(cur, 0) + 1);
            }

            return (int) ans;

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}