package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _433MinimumGeneticMutation {
    public static void main(String[] args) {
        Solution t = new _433MinimumGeneticMutation().new Solution();
        String start = "AACCGGTT", end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        System.out.println(t.minMutation(start, end, bank));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 直接运行bfs，
         * 1. 从start开始，每一次变更一个位置的字符
         * 2. 变更后的字符在bank中，则步数+1，继续下一步；不在bank中，直接跳过（用map记录str和步数step的关系）
         * 3. 在2中，注意一个字符串已经在map中存在了，那么直接跳过即可；原因是肯定是之前某一步转换中塞到map的，而bfs决定了前面的转换需要的步数一定不大于后面的转换
         * *
         * 时间复杂度为O(Cnm)，其中n为基因序列bank的长度，m为数组bank的长度，每个位置有C=4种可能
         */
        public int minMutation1(String startGene, String endGene, String[] bank) {
            Map<String, Integer> strStepMap = new HashMap<>();
            Deque<String> deque = new ArrayDeque<>();
            Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
            strStepMap.put(startGene, 0);
            deque.addLast(startGene);
            int n = startGene.length();
            char[] cans = {'A', 'C', 'G', 'T'};
            while (!deque.isEmpty()) {
                String str = deque.poll();
                int step = strStepMap.get(str);
                if (str.equals(endGene)) {
                    return step;
                }
                StringBuilder sb = new StringBuilder(str);
                for (int i = 0; i < n; i++) {
                    char c = sb.charAt(i);
                    for (int j = 0; j < 4; j++) {
                        if (c == cans[j]) {
                            continue;
                        }
                        sb.setCharAt(i, cans[j]);
                        if (bankSet.contains(sb.toString()) && !strStepMap.containsKey(sb.toString())) {
                            strStepMap.put(sb.toString(), step + 1);
                            deque.addLast(sb.toString());
                        }
                        sb.setCharAt(i, c);
                    }
                }
            }
            return -1;
        }

        /**
         * 可以根据bank创建一个合法的邻接矩阵，从start到end的过程就从合法的邻接矩阵
         * <p>
         * 时间复杂度为O(m*n^2)，其中n为基因序列bank的长度，m为数组bank的长度；其中主要耗时在创建邻接矩阵，消耗m*n^2时间
         * 后续的BFS，因为queue中的元素最多n个，时间复杂度为O(n)
         */
        public int minMutation(String startGene, String endGene, String[] bank) {
            int bankLen = bank.length;
            int strLen = startGene.length();
            // 存放i位置的字符串与哪些位置的字符串邻接
            List<Integer>[] adj = new List[bankLen];
            for (int i = 0; i < bankLen; i++) {
                adj[i] = new ArrayList<>();
            }
            // 存放在bank中的下标
            Deque<Integer> deque = new ArrayDeque<>();
            Map<Integer, Integer> strStepMap = new HashMap<>();
            boolean[] visited = new boolean[bankLen];
            // 优化：在构建adj时，需要遍历bank中的所有元素，此时当bank[i]==end时，记录下endIndex坐标
            // 若全程endIndex都没被改变，则表示bank中无end字符串，直接返回-1
            int endIndex = -1;
            for (int i = 0; i < bankLen; i++) {
                if (bank[i].equals(endGene)) {
                    endIndex = i;
                }
                for (int j = i + 1; j < bankLen; j++) {
                    int mutNum = 0;
                    for (int k = 0; k < strLen; k++) {
                        if (bank[i].charAt(k) != bank[j].charAt(k)) {
                            mutNum++;
                        }
                        // bank中i和j位置的两个字符串的元素差值大于1，直接提前退出循环
                        if (mutNum > 1) {
                            break;
                        }
                    }
                    // 遍历完成bank[j]后，查看i和j是否只差一个字符；是则填入邻接数组中
                    if (mutNum == 1) {
                        adj[i].add(j);
                        adj[j].add(i);
                    }
                }
                int mutNum = 0;
                // 同时查看bank[i]和start的字符数是否差一个，是的话就作为bfs的起点
                for (int k = 0; k < strLen; k++) {
                    if (bank[i].charAt(k) != startGene.charAt(k)) {
                        mutNum++;
                    }
                    // bank中i和j位置的两个字符串的元素差值大于1，直接提前退出循环
                    if (mutNum > 1) {
                        break;
                    }
                }
                if (mutNum == 1) {
                    deque.offerLast(i);
                    strStepMap.put(i, 1);
                    visited[i] = true;
                }
            }
            if (endIndex == -1) {
                return endIndex;
            }

            while (!deque.isEmpty()) {
                int curIndex = deque.pollFirst();
                int step = strStepMap.get(curIndex);
                // 可以用curIndex==endIndex代替
//                if (bank[curIndex].equals(endGene)) {
//                    return step;
//                }
                if (curIndex == endIndex) {
                    return step;
                }
                for (int i : adj[curIndex]) {
                    if (visited[i]) {
                        continue;
                    }
                    deque.offerLast(i);
                    strStepMap.put(i, step + 1);
                    visited[i] = true;
                }
            }

            return -1;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
