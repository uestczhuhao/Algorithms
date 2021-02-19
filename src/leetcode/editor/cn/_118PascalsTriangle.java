package leetcode.editor.cn;

//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//] 
// Related Topics 数组 
// 👍 453 👎 0


import java.util.ArrayList;
import java.util.List;

public class _118PascalsTriangle {
    public static void main(String[] args) {
        Solution t = new _118PascalsTriangle().new Solution();
        System.out.println(t.generate(5));
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> answer = new ArrayList<>();
            if (numRows <= 0) {
                return answer;
            }

            List<Integer> preLevel = new ArrayList<>();
            List<Integer> curLevel;
            preLevel.add(1);
            answer.add(new ArrayList<>(preLevel));
            for (int i = 2; i <= numRows; i++) {
                curLevel = new ArrayList<>();
                for (int j = 1; j <= i; j++) {
                    if (j == 1 || j == i) {
                        curLevel.add(1);
                    } else {
                        curLevel.add(preLevel.get(j - 1) + preLevel.get(j - 2));
                    }
                }
                answer.add(new ArrayList<>(curLevel));
                preLevel = curLevel;
            }

            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}