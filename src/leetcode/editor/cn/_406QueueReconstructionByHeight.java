package leetcode.editor.cn;

//假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 
//个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。 
//
// 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第
// j 个人的属性（queue[0] 是排在队列前面的人）。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
//输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
//解释：
//编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
//编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
//编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
//编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
//编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
//编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
//因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
// 
//
// 示例 2： 
//
// 
//输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
//输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= people.length <= 2000 
// 0 <= hi <= 106 
// 0 <= ki < people.length 
// 题目数据确保队列可以被重建 
// 
// Related Topics 贪心算法 
// 👍 754 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class _406QueueReconstructionByHeight {
    public static void main(String[] args) {
        Solution t = new _406QueueReconstructionByHeight().new Solution();
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：先按个头逆序排，个头一样的按ki正序排
         * 理由是个头相同，前面有2个的A一定排在前面有3个人B前面，因为对B来说，A也满足题意
         */
        public int[][] reconstructQueue1(int[][] people) {
            List<int[]> reconstructList = new ArrayList<>();
            Arrays.sort(people, (person1, person2) -> {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            });

            for (int[] person : people) {
                // 确定了插入顺序是从高到底，
                // 当第i个插入时，比他高的都已经各就各位了，
                // 那么把第i个人直接插入到ki位置，其前面的k-1个比i之前插入的，一定比第i个人高（或等于）
                reconstructList.add(person[1], person);
            }

            return reconstructList.toArray(new int[reconstructList.size()][]);
        }

        /**
         * 从小到大排序，个头矮的先站，个头相同的按ki逆序
         * 理由是如果A，B个头都一样，ka=3，kb=4，则B应该站在A的后面，因此要先安排B，留出位置给A
         * <p>
         * 在放第i人时，需要将其放入第ki+1个空位置（理由是前i-1个已经各就各位，但不影响i）
         * 其前面要预留ki个空位置给后续比他高的人站
         */
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, (person1, person2) -> {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];
                } else {
                    return person2[1] - person1[1];
                }
            });

            int[][] result = new int[people.length][];
            for (int[] person : people) {
                // 留ki个空位置，等效为放到第ki + 1个空位置
                int position = person[1] + 1;
                for (int i = 0; i < people.length; i++) {
                    if (result[i] == null) {
                        position --;
                        // 找到了第ki+1个空位置
                        if (position == 0) {
                            result[i] = person;
                        }
                    }
                }
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}