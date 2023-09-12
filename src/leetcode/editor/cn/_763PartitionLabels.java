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
//        System.out.println(t.partitionLabels("ababcbacadefegdehijhklij"));
//        System.out.println(t.partitionLabels("eccbbbbdec"));
        System.out.println(t.partitionLabels("abac"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 可优化
         * 1.不用记住每个字母的start位置，因为不用合并，只需记住end即可
         * 2.遍历字符串，当在位置i时，0~i-1已经遍历完，已经知道它们有哪些字母（如有a c e），由于在1时已经知道这几个字母的最远距离
         * 则在位置i时，取这3个字母的最远值的最大值，作为当前段的最远距离即可（中间不能断，否则一个字母可能会出现在两段）
         * 3.当 i==max(end)时，这一段就可到尾部了（因为0~i的所有字母的最远距离就是当前的i），ans取 max(end) - start + 1，下一段从i+1开始
         */
        public List<Integer> partitionLabels(String s) {
            int[] charPosition = new int[26];
            for (int i = 0; i < s.length(); i++) {
                charPosition[s.charAt(i) - 'a'] = i;
            }
            List<Integer> ans = new LinkedList<>();
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                end = Math.max(end, charPosition[s.charAt(i) - 'a']);
                if (i == end) {
                    ans.add(end - start + 1);
                    start = end +1;
                }
            }
            return ans;
        }

        // 合并有序数组，不用实际做合并，取最右端点即可
        public List<Integer> partitionLabels1(String s) {
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
