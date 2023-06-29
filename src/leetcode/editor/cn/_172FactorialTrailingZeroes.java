package leetcode.editor.cn;

public class _172FactorialTrailingZeroes {
    public static void main(String[] args) {
        Solution t = new _172FactorialTrailingZeroes().new Solution();
        System.out.println(t.trailingZeroes(6));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 尾数为0的个数取决于因子中10的个数，即取决于2和5（质因子）的个数，
         * 而因子中的5的个数小于等于2的个数，因此只需要关注5的个数
         * 求出[1, n]中的所有质因子为5的个数，相加即为答案
         */
        public int trailingZeroes1(int n) {
            int ans = 0;
            for (int i = 5; i <= n; i++) {
                for (int j = i; j % 5 == 0; j /= 5) {
                    ans++;
                }
            }
            return ans;
        }

        /**
         *  尾数为0的个数取决于因子中10的个数，即取决于2和5（质因子）的个数，
         *  1. 求[1, n] 中的质因子p的个数n1，即p，2p，3p....n，一共有 n / p 向下取整 个
         *  2. 接着找因子p^2的个数n2，即 p^2，2p^2，3p^2....n，这部分又满足1，因此取n2即可（本来应该去2*n2，但是都在1中算过了，因此只取n2即可）
         *  3. 找到p^3的个数n3
         *  ... ...
         *  因此共有 n/p + n/p^2 + n/p^3 + ... + n/p^k
         *  把p换成5，上面的累加和为 n/4，即O(n) 复杂度
         *
         *  优化：由于 n/5^k = n/5^(k-1)/5 = ... = n/5/5^(k-1)，因此累加时，可以从 n/5 （n1）开始计算，最后到n=0 循环终止
         */
        public int trailingZeroes(int n) {
            int ans = 0;
            while (n > 0) {
                n /= 5;
                ans += n;
            }
            return ans;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
