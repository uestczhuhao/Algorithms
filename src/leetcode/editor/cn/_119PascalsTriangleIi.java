package leetcode.editor.cn;

//给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
//
//
//
//
//
// 示例 1:
//
//
//输入: rowIndex = 3
//输出: [1,3,3,1]
//
//
// 示例 2:
//
//
//输入: rowIndex = 0
//输出: [1]
//
//
// 示例 3:
//
//
//输入: rowIndex = 1
//输出: [1,1]
//
//
//
//
// 提示:
//
//
// 0 <= rowIndex <= 33
//
//
//
//
// 进阶：
//
// 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
// Related Topics 数组 动态规划 👍 344 👎 0


import java.util.LinkedList;
import java.util.List;

public class _119PascalsTriangleIi {
    public static void main(String[] args) {
        Solution t = new _119PascalsTriangleIi().new Solution();
//        System.out.println(t.getRow(0));
//        System.out.println(t.getRow(1));
//        System.out.println(t.getRow(2));
        System.out.println(t.getRow(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 进一步优化，从后往前遍历，不会覆盖要更新的值
         */
        public List<Integer> getRow(int rowIndex) {
            List<Integer> ans = new LinkedList<>();
            ans.add(1);
            for (int i = 1; i <= rowIndex; i++) {
                for (int j = ans.size() - 1; j > 0; j--) {
                    ans.set(j, ans.get(j) + ans.get(j - 1));
                }
                ans.add(1);
            }

            return ans;
        }

        /**
         * 优化空间，pre数组可以省略
         */
        public List<Integer> getRow1(int rowIndex) {
            List<Integer> ans = new LinkedList<>();
            ans.add(1);
            int pre;
            int tmp;
            for (int i = 1; i <= rowIndex; i++) {
                pre = 1;
                for (int j = 1; j < ans.size(); j++) {
                    // 注意：在第j个位置更新前，要把这个值记录下来，因为更新i+1时要用到
                    tmp = ans.get(j);
                    ans.set(j, ans.get(j) + pre);
                    pre = tmp;
                }
                ans.add(1);
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
