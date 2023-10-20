package leetcode.editor.cn;

public class _201BitwiseAndOfNumbersRange {
    public static void main(String[] args) {
        Solution t = new _201BitwiseAndOfNumbersRange().new Solution();
//        System.out.println(t.rangeBitwiseAnd(1, 2147483647));
//        System.out.println(t.rangeBitwiseAnd(2147483647, 2147483647));
        System.out.println(t.rangeBitwiseAnd(5, 7));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 思路相同，但可以用更有效率的方式求前缀和
        // 把left和right一起向右移位，直到二者相等
        // 留下的就是left==right==前缀和，同时记录下来移位了多少位，再向左移位回到原位置即可
        // （此步等效为消除前缀和右边的所有位并补零）
        public int rangeBitwiseAnd(int left, int right) {
            int shift = 0;
            // 找到公共前缀
            while (left < right) {
                left >>= 1;
                right >>= 1;
                ++shift;
            }
            return left << shift;
        }

        // 能够证明，只要left和right的二进制前缀一致，则结果就是其前缀的与的值
        // https://leetcode.cn/problems/bitwise-and-of-numbers-range/solutions/385466/ju-hao-li-jie-de-wei-yun-suan-si-lu-by-time-limit/?envType=study-plan-v2&envId=top-interview-150
        public int rangeBitwiseAnd1(int left, int right) {
            int[] indexLeft = new int[31];
            int[] indexRight = new int[31];
            int i = 0;
            while (left != 0) {
                indexLeft[i++] = left & 1;
                left >>>= 1;
            }
            i = 0;
            while (right != 0) {
                indexRight[i++] = right & 1;
                right >>>= 1;
            }
            int ans = 0;
            i = 30;
            while (i >= 0 && indexLeft[i] == indexRight[i]) {
                if (indexLeft[i] == 1) {
                    ans += Math.pow(2, i);
                }
                i--;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
