package leetcode.editor.cn;

//给你一个 n 个节点的树（也就是一个无环连通无向图），节点编号从 0 到 n - 1 ，且恰好有 n - 1 条边，每个节点有一个值。树的 根节点 为 0 
//号点。 
//
// 给你一个整数数组 nums 和一个二维数组 edges 来表示这棵树。nums[i] 表示第 i 个点的值，edges[j] = [uj, vj] 表示节
//点 uj 和节点 vj 在树中有一条边。 
//
// 当 gcd(x, y) == 1 ，我们称两个数 x 和 y 是 互质的 ，其中 gcd(x, y) 是 x 和 y 的 最大公约数 。 
//
// 从节点 i 到 根 最短路径上的点都是节点 i 的祖先节点。一个节点 不是 它自己的祖先节点。 
//
// 请你返回一个大小为 n 的数组 ans ，其中 ans[i]是离节点 i 最近的祖先节点且满足 nums[i] 和 nums[ans[i]] 是 互质的 
//，如果不存在这样的祖先节点，ans[i] 为 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
//输出：[-1,0,0,1]
//解释：上图中，每个节点的值在括号中表示。
//- 节点 0 没有互质祖先。
//- 节点 1 只有一个祖先节点 0 。它们的值是互质的（gcd(2,3) == 1）。
//- 节点 2 有两个祖先节点，分别是节点 1 和节点 0 。节点 1 的值与它的值不是互质的（gcd(3,3) == 3）但节点 0 的值是互质的(gcd(
//2,3) == 1)，所以节点 0 是最近的符合要求的祖先节点。
//- 节点 3 有两个祖先节点，分别是节点 1 和节点 0 。它与节点 1 互质（gcd(3,2) == 1），所以节点 1 是离它最近的符合要求的祖先节点。
//
// 
//
// 示例 2： 
//
// 
//
// 
//输入：nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
//输出：[-1,0,-1,0,0,0,-1]
// 
//
// 
//
// 提示： 
//
// 
// nums.length == n 
// 1 <= nums[i] <= 50 
// 1 <= n <= 105 
// edges.length == n - 1 
// edges[j].length == 2 
// 0 <= uj, vj < n 
// uj != vj 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 数学 
// 👍 13 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class _1766TreeOfCoprimes {
    public static void main(String[] args) {
        Solution t = new _1766TreeOfCoprimes().new Solution();
        int[] nums = {9,16,30,23,33,35,9,47,39,46,16,38,5,49,21,44,17,1,6,37,49,15,23,46,38,9,27,3,24,1,14,17,12,23,43,38,12,4,8,17,11,18,26,22,49,14,9};
        int[][] edges = {{17,0},{30,17},{41,30},{10,30},{13,10},{7,13},{6,7},{45,10},{2,10},{14,2},{40,14},{28,40},{29,40},{8,29},{15,29},{26,15},{23,40},{19,23},{34,19},{18,23},{42,18},{5,42},{32,5},{16,32},{35,14},{25,35},{43,25},{3,43},{36,25},{38,36},{27,38},{24,36},{31,24},{11,31},{39,24},{12,39},{20,12},{22,12},{21,39},{1,21},{33,1},{37,1},{44,37},{9,44},{46,2},{4,46}};
        System.out.println(Arrays.toString(t.getCoprimes(nums, edges)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // f_g.get(i)是一个list，list是i的互质数(1-50范围),其实就是一个图
        List<List<Integer>> f_g = new LinkedList<>();
        // 结果矩阵
        int[] res;
        // 题目中给定的树/图
        List<List<Integer>> G = new ArrayList<>();
        int n;
        int[] nums;
        /*
         * 大佬代码中的pair
         * val_level[i] ：值为i的元素出现的level
         * idx[i] : 值为i的元素作为最近父亲出现的点的索引值
         * */
        int[] val_level = new int[51];
        int[] idx = new int[51];


        /**
         * 题解：https://leetcode-cn.com/problems/tree-of-coprimes/solution/hu-zhi-shu-xiang-jie-ti-mu-de-qie-ru-dia-poyw/
         */
        public int[] getCoprimes(int[] nums, int[][] edges) {
            this.nums = nums;
            res = new int[nums.length];
            Arrays.fill(res, -1);
            // 默认不出现
            Arrays.fill(idx, -1);
            n = nums.length;
            // 建图开始
            for (int i = 0; i < 51; i++) {
                f_g.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                G.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                G.get(edge[0]).add(edge[1]);
                G.get(edge[1]).add(edge[0]);
            }
            for (int i = 1; i < 51; i++) {
                for (int j = i; j < 51; j++) {
                    if (gcd(i, j) == 1) {
                        f_g.get(i).add(j);
                        f_g.get(j).add(i);
                    }
                }
            }
            // 建图结束
            dfs(0, -1, 1);
            return res;
        }

        public void dfs(int cur, int pre, int level) {
            int val = nums[cur];
            int latest_level = 0;
            int latest_val = -1;
            for (int maybe : f_g.get(val)) {
                if (val_level[maybe] > latest_level) {
                    latest_level = val_level[maybe];
                    latest_val = maybe;
                }
            }
            res[cur] = (latest_val == -1) ? -1 : idx[latest_val];
            // 下面的实现和大佬的不一样，我采用了系统栈保存结果，大佬用的显式栈
            int origin_level = val_level[val];
            int origin_idx = idx[val];
            val_level[val] = level;
            idx[val] = cur;
            for (int next : G.get(cur)) {
                if (next != pre) {
                    dfs(next, cur, level + 1);
                }
            }
            val_level[val] = origin_level;
            idx[val] = origin_idx;
        }

        // 网上百度的gcd算法
        public int gcd(int m, int n) {
            if (m < n) {
                int k = m;
                m = n;
                n = k;
            }
            return m % n == 0 ? n : gcd(n, m % n);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}