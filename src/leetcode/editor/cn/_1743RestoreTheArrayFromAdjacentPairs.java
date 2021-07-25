package leetcode.editor.cn;

//存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。 
//
// 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui
// 和 vi 在 nums 中相邻。 
//
// 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i]
//, nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。 
//
// 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。 
//
// 
//
// 示例 1： 
//
// 
//输入：adjacentPairs = [[2,1],[3,4],[3,2]]
//输出：[1,2,3,4]
//解释：数组的所有相邻元素对都在 adjacentPairs 中。
//特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
// 
//
// 示例 2： 
//
// 
//输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
//输出：[-2,4,1,-3]
//解释：数组中可能存在负数。
//另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
// 
//
// 示例 3： 
//
// 
//输入：adjacentPairs = [[100000,-100000]]
//输出：[100000,-100000]
// 
//
// 
//
// 提示： 
//
// 
// nums.length == n 
// adjacentPairs.length == n - 1 
// adjacentPairs[i].length == 2 
// 2 <= n <= 105 
// -105 <= nums[i], ui, vi <= 105 
// 题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums 
// 
// Related Topics 数组 哈希表 
// 👍 69 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _1743RestoreTheArrayFromAdjacentPairs {
    public static void main(String[] args) {
        Solution t = new _1743RestoreTheArrayFromAdjacentPairs().new Solution();
        int[][] adjs = {{4, -10}, {-1, 3}, {4, -3}, {-3, 3}};
        System.out.println(Arrays.toString(t.restoreArray(adjs)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] restoreArray1(int[][] adjacentPairs) {
            Map<Integer, List<Integer>> adjMap = new HashMap<>();
            for (int[] adjPair : adjacentPairs) {
                int src = adjPair[0];
                int tgt = adjPair[1];
                List<Integer> sAdj = adjMap.getOrDefault(src, new ArrayList<>());
                List<Integer> tAdj = adjMap.getOrDefault(tgt, new ArrayList<>());
                sAdj.add(tgt);
                tAdj.add(src);
                adjMap.put(src, sAdj);
                adjMap.put(tgt, tAdj);

            }

            int len = adjacentPairs.length + 1;
            int[] ans = new int[len];
            int start = -1, end = -1;
            for (Map.Entry<Integer, List<Integer>> entry : adjMap.entrySet()) {
                if (entry.getValue().size() == 1) {
                    if (start == -1) {
                        start = entry.getKey();
                    } else {
                        end = entry.getKey();
                    }
                }
            }
            int index = 0, pre = start;
            ans[index++] = start;
            Set<Integer> visit = new HashSet<>();
            visit.add(start);
            visit.add(end);
            while (index < len - 1) {
                List<Integer> preAdj = adjMap.get(pre);
                int cur = 0;
                for (int ad : preAdj) {
                    if (!visit.contains(ad)) {
                        cur = ad;
                        break;
                    }
                }
                visit.add(cur);
                ans[index] = cur;
                pre = cur;
                index++;
            }

            ans[len - 1] = end;
            return ans;
        }

        /**
         * 时间复杂度一样的优化
         */
        public int[] restoreArray(int[][] adjacentPairs) {
            Map<Integer, List<Integer>> adjMap = new HashMap<>();
            for (int[] adjPair : adjacentPairs) {
                int src = adjPair[0];
                int tgt = adjPair[1];
                adjMap.putIfAbsent(src, new ArrayList<>());
                adjMap.putIfAbsent(tgt, new ArrayList<>());
                adjMap.get(src).add(tgt);
                adjMap.get(tgt).add(src);
            }

            int len = adjacentPairs.length + 1;
            int[] ans = new int[len];
            int start = -1;
            for (Map.Entry<Integer, List<Integer>> entry : adjMap.entrySet()) {
                if (entry.getValue().size() == 1) {
                    start = entry.getKey();
                    break;
                }
            }
            ans[0] = start;
            ans[1] = adjMap.get(start).get(0);
            for (int i = 2; i < len; i++) {
                List<Integer> list = adjMap.get(ans[i - 1]);
                ans[i] = list.get(0) == ans[i - 2] ? list.get(1) : list.get(0);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}