package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class _763PartitionLabels {
    public static void main(String[] args) {
        Solution t = new _763PartitionLabels().new Solution();
        System.out.println(t.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(t.partitionLabels("eccbbbbdec"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 合并有序数组，不用实际做合并，取最右端点即可
        public List<Integer> partitionLabels(String s) {
            int[][] charPosition = new int[26][2];
            for (int i = 0; i < 26; i++) {
                charPosition[i] = new int[] {-1, -1};
            }
            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                if (charPosition[cur - 'a'][0] == -1) {
                    charPosition[cur - 'a'][0] = i;
                    charPosition[cur - 'a'][1] = i;
                } else {
                    charPosition[cur - 'a'][1] = i;
                }
            }
            // 有序数组合并
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            for (int[] pos : charPosition) {
                if (pos[0] != -1) {
                    queue.add(pos);
                }
            }
            List<Integer> ans = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] segment = queue.poll();
                int start = segment[0];
                int end = segment[1];
                while (!queue.isEmpty() && queue.peek()[0] <= end) {
                    end = Math.max(queue.poll()[1], end);
                }
                ans.add(end - start + 1);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
