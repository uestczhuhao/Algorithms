package leetcode.editor.cn;

//给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values
//[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。 
//
// 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj =
// ? 的结果作为答案。 
//
// 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替
//代这个答案。 
//
// 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"]
//,["b","a"],["a","e"],["a","a"],["x","x"]]
//输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
//解释：
//条件：a / b = 2.0, b / c = 3.0
//问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
// 
//
// 示例 2： 
//
// 
//输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], quer
//ies = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//输出：[3.75000,0.40000,5.00000,0.20000]
// 
//
// 示例 3： 
//
// 
//输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a
//","c"],["x","y"]]
//输出：[0.50000,2.00000,-1.00000,-1.00000]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= equations.length <= 20 
// equations[i].length == 2 
// 1 <= Ai.length, Bi.length <= 5 
// values.length == equations.length 
// 0.0 < values[i] <= 20.0 
// 1 <= queries.length <= 20 
// queries[i].length == 2 
// 1 <= Cj.length, Dj.length <= 5 
// Ai, Bi, Cj, Dj 由小写英文字母与数字组成 
// 
// Related Topics 并查集 图 
// 👍 461 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class _399EvaluateDivision {
    public static void main(String[] args) {
        Solution t = new _399EvaluateDivision().new Solution();
        List<List<String>> equations = new LinkedList<>();
        List<String> e1 = new LinkedList<>();
        List<String> e2 = new LinkedList<>();
        equations.add(e1);
        equations.add(e2);
        e1.add("a");
        e1.add("b");
        e2.add("b");
        e2.add("c");
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new LinkedList<>();
        List<String> e3 = new LinkedList<>();
        List<String> e4 = new LinkedList<>();
        List<String> e5 = new LinkedList<>();
        queries.add(e3);
        queries.add(e4);
        queries.add(e5);
        e3.add("a");
        e3.add("c");
        e4.add("b");
        e4.add("a");
        e5.add("a");
        e5.add("e");
        System.out.println(Arrays.toString(t.calcEquation(equations, values, queries)));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：带权值的并查集，union时注意计算weight
         * 对输入equation的每个值编码，从0-2 * len-1，在构建并查集时可以使用数组加速
         * 第 i 个元素表示输入的第 i 个节点
         */
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            if (equations == null || equations.size() == 0) {
                return null;
            }

            int nodeNum = 2 * equations.size();
            UnionFind unionFind = new UnionFind(nodeNum);
            int index = 0;
            // 用map加速过滤没有出现过的节点
            Map<String, Integer> nodeIndexMap = new HashMap<>();
            // 构建并查集
            for (int i = 0; i < equations.size(); i++) {
                List<String> curPair = equations.get(i);
                String node1 = curPair.get(0);
                String node2 = curPair.get(1);

                if (!nodeIndexMap.containsKey(node1)) {
                    nodeIndexMap.put(node1, index);
                    index++;
                }

                if (!nodeIndexMap.containsKey(node2)) {
                    nodeIndexMap.put(node2, index);
                    index++;
                }

                unionFind.union(nodeIndexMap.get(node1), nodeIndexMap.get(node2), values[i]);
            }

            // 查询
            double[] result = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                List<String> curQuery = queries.get(i);
                String target1 = curQuery.get(0);
                String target2 = curQuery.get(1);
                if (!nodeIndexMap.containsKey(target1) || !nodeIndexMap.containsKey(target2)) {
                    result[i] = -1.0D;
                } else {
                    result[i] = unionFind.computeWeight(nodeIndexMap.get(target1), nodeIndexMap.get(target2));
                }
            }

            return result;

        }

        private class UnionFind {
            int size;
            /**
             * 存放父节点
             */
            int[] parent;
            /**
             * 存放权值
             */
            double[] weight;

            public UnionFind(int n) {
                this.size = n;
                parent = new int[size];
                weight = new double[size];
                for (int i = 0; i < size; i++) {
                    // 初始化为自身
                    parent[i] = i;
                    weight[i] = 1.0D;
                }
            }

            /**
             * 输入为 src / tgt = value
             */
            public void union(int src, int tgt, double value) {
                int sRoot = find(src);
                int tRoot = find(tgt);
                if (sRoot == tRoot) {
                    return;
                }

                parent[sRoot] = tRoot;
                // 推导过程见：https://leetcode-cn.com/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/
                weight[sRoot] = weight[tgt] * value / weight[src];
            }

            public int find(int target) {
                if (parent[target] != target) {
                    int originWeight = parent[target];
                    parent[target] = find(parent[target]);
                    weight[target] *= weight[originWeight];
                }

                return parent[target];
            }

            public double computeWeight(int src, int tgt) {
                int sRoot = find(src);
                int tRoot = find(tgt);
                if (sRoot == tRoot) {
                    return weight[src] / weight[tgt];
                } else {
                    return -1.0D;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}