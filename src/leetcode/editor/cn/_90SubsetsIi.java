package leetcode.editor.cn;

//给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: [1,2,2]
//输出:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//] 
// Related Topics 数组 回溯算法 
// 👍 381 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _90SubsetsIi {
    public static void main(String[] args) {
        Solution t = new _90SubsetsIi().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> answer = new LinkedList<>();
        int[] numArr;

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            if (nums == null || nums.length == 0) {
                return answer;
            }

            Arrays.sort(nums);
            numArr = nums;
            dfs(0, new LinkedList<>());
            return answer;
        }

        private void dfs(int start, LinkedList<Integer> curSubset) {
            answer.add(new ArrayList<>(curSubset));
            for (int i = start; i < numArr.length; i++) {
                // 跳过相同元素
                if (i > start && numArr[i] == numArr[i-1]) {
                    continue;
                }

                curSubset.addLast(numArr[i]);
                // 递归调用下一个元素
                dfs(i + 1, curSubset);
                curSubset.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}