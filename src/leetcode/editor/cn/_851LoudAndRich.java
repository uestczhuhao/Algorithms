package leetcode.editor.cn;

//有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。为了方便起见，我们将编号
//为 x 的人简称为 "person x "。 
//
// 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。另给你一个整数数组
// quiet ，其中 quiet[i] 是 person i 的安静值。richer 中所给出的数据 逻辑自恰（也就是说，在 person x 比 
//person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。 
//
// 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，
//person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。 
//
// 
//
// 示例 1： 
//
// 
//输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,
//7,0]
//输出：[5,5,2,5,4,5,6,7]
//解释： 
//answer[0] = 5，
//person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
//
//唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
//但是目前还不清楚他是否比 person 0 更有钱。
//answer[7] = 7，
//在所有拥有的钱肯定不少于 person 7 的人中（这可能包括 person 3，4，5，6 以及 7），
//最安静（有较低安静值 quiet[x]）的人是 person 7。
//其他的答案也可以用类似的推理来解释。
// 
//
// 示例 2： 
//
// 
//输入：richer = [], quiet = [0]
//输出：[0]
// 
// 
//
// 提示： 
//
// 
// n == quiet.length 
// 1 <= n <= 500 
// 0 <= quiet[i] < n 
// quiet 的所有值 互不相同 
// 0 <= richer.length <= n * (n - 1) / 2 
// 0 <= ai, bi < n 
// ai != bi 
// richer 中的所有数对 互不相同 
// 对 richer 的观察在逻辑上是一致的 
// 
// Related Topics 深度优先搜索 图 拓扑排序 数组 👍 131 👎 0


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class _851LoudAndRich {
    public static void main(String[] args) {
        Solution t = new _851LoudAndRich().new Solution();
        int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
        System.out.println(Arrays.toString(t.loudAndRich(richer, quiet)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] loudAndRich(int[][] richer, int[] quiet) {
            int num = quiet.length;
            // 入度矩阵，最富的人入度为0
            int[] inDegree = new int[num];
            // 邻接矩阵，边为富人指向穷人
            boolean[][] adj = new boolean[num][num];
            for (int[] r : richer) {
                int rich = r[0];
                int poor = r[1];
                inDegree[poor]++;
                adj[rich][poor] = true;
            }

            Deque<Integer> deque = new LinkedList<>();
            int[] ans = new int[num];
            for (int i = 0; i < num; i++) {
                ans[i] = i;
                if (inDegree[i] == 0) {
                    deque.offerLast(i);
                }
            }

            while (!deque.isEmpty()) {
                Integer rich = deque.pollFirst();
                for (int poor = 0; poor < num; poor++) {
                    if (adj[rich][poor]) {
                        if (--inDegree[poor] == 0) {
                            deque.offerLast(poor);
                        }

                        // 从富到穷做拓补排序，同时更新穷人的ans，其中ans[rich] 是从比他有钱（或自己）的人中选取的最安静的那一个，所以可以逐级传递
                        if (quiet[ans[rich]] < quiet[ans[poor]]) {
                            ans[poor] = ans[rich];
                        }
                    }
                }
            }

            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}