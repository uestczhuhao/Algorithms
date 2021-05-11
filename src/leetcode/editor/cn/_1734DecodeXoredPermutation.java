package leetcode.editor.cn;

//给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。 
//
// 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说
//，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。 
//
// 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。 
//
// 
//
// 示例 1： 
//
// 输入：encoded = [3,1]
//输出：[1,2,3]
//解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
// 
//
// 示例 2： 
//
// 输入：encoded = [6,5,4,6]
//输出：[2,4,1,5,3]
// 
//
// 
//
// 提示： 
//
// 
// 3 <= n < 105 
// n 是奇数。 
// encoded.length == n - 1 
// 
// Related Topics 位运算 
// 👍 52 👎 0


import java.util.Arrays;

public class _1734DecodeXoredPermutation {
    public static void main(String[] args) {
        Solution t = new _1734DecodeXoredPermutation().new Solution();
        int[] encoded = {3, 12, 1, 15, 5, 2, 3, 7};
        System.out.println(Arrays.toString(t.decode(encoded)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 找到perm[0]，利用异或的性质，x ^ y = a，则 x ^ a = x ^ x ^ y = y
         * 暴力找到perm[0]，一个个试，不合法的剪枝，超时
         */
        public int[] decode1(int[] encoded) {
            int len = encoded.length + 1;
            int[] answer = new int[len];
            int[] visit;
            for (int i = 1; i <= len; i++) {
                visit = new int[len + 1];
                answer = new int[len];
                answer[0] = i;
                int j = 1;
                for (; j < len; j++) {
                    int cur = answer[j - 1] ^ encoded[j - 1];
                    if (cur == 0 || cur > len || visit[cur] > 0) {
                        break;
                    }
                    answer[j] = cur;
                    visit[cur]++;
                }
                if (j == len) {
                    return answer;
                }
            }

            return answer;
        }

        /**
         * 思路同上，巧妙找到perm[0]，先求1-n的异或 total = 1 ^ 2 ^ 3 ^ ... ^ n
         * 在想办法找到 perm[1] ^ perm[2] ^ ... ^ perm[n]，
         * 根据题示条件，perm数组取值在1-n之间，即可确定perm[0]
         */
        public int[] decode(int[] encoded) {
            int n = encoded.length + 1;
            int total = 1;
            for (int i = 2; i <= n; i++) {
                total ^= i;
            }

            // 取encoded的奇数项，即为 perm[1] ^ perm[2] ^ ... ^ perm[n]
            int odd = encoded[1];
            for (int i = 3; i < encoded.length; i += 2) {
                odd ^= encoded[i];
            }

            int[] perm = new int[n];
            perm[0] = total ^ odd;
            for (int i = 1; i < n; i++) {
                perm[i] = perm[i - 1] ^ encoded[i - 1];
            }

            return perm;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}