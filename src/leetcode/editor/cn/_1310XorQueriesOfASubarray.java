package leetcode.editor.cn;

//有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。 
//
// 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为
//本次查询的结果。 
//
// 并返回一个包含给定查询 queries 所有结果的数组。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
//输出：[2,7,14,8] 
//解释：
//数组中元素的二进制表示形式是：
//1 = 0001 
//3 = 0011 
//4 = 0100 
//8 = 1000 
//查询的 XOR 值为：
//[0,1] = 1 xor 3 = 2 
//[1,2] = 3 xor 4 = 7 
//[0,3] = 1 xor 3 xor 4 xor 8 = 14 
//[3,3] = 8
// 
//
// 示例 2： 
//
// 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
//输出：[8,0,4,4]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 3 * 10^4 
// 1 <= arr[i] <= 10^9 
// 1 <= queries.length <= 3 * 10^4 
// queries[i].length == 2 
// 0 <= queries[i][0] <= queries[i][1] < arr.length 
// 
// Related Topics 位运算 
// 👍 71 👎 0


public class _1310XorQueriesOfASubarray {
    public static void main(String[] args) {
        Solution t = new _1310XorQueriesOfASubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] xorQueries(int[] arr, int[][] queries) {
            // 存放前i个值的异或结果
            int[] preXor = new int[arr.length];
            preXor[0] = arr[0];
            for (int i = 1; i <arr.length;i++) {
                preXor[i] = preXor[i - 1] ^ arr[i];
            }

            int[] ans = new int[queries.length];
            int start, end;
            for (int i=0;i<queries.length;i++) {
                start = queries[i][0];
                end = queries[i][1];
                ans[i] = start == 0 ? preXor[end] : preXor[end] ^ preXor[start - 1];
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}