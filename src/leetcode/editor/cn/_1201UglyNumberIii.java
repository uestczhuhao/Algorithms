package leetcode.editor.cn;

public class _1201UglyNumberIii {
    public static void main(String[] args) {
        Solution t = new _1201UglyNumberIii().new Solution();
        System.out.println(t.nthUglyNumber(1000000000, 2, 168079517, 403313907));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * https://leetcode.cn/problems/ugly-number-iii/solutions/2003797/javac-rong-chi-yuan-li-er-fen-cha-zhao-b-bf69/
         * 二分法，主要思路：找到最小值和最大值，判断其中间的值mid是否是第n个丑数
         * 如何确定mid是第几个丑数，求出mid/a + mid/b + mid/c，而前面的加法对了ab、ac、bc和abc的公倍数都加了2次（abc的倍数加了3次）
         * 根据容斥原理，答案应该是mid/a + mid/b + mid/c - mid/ab - mid/ac - mid/bc + mid/abc
         * 其中ab、ac、bc和abc表示最小公倍数
         * 为什么是最小公倍数？因为如果mid包含了ab的其他倍数，比如mid/ab = 8，它包含了mid/2ab，mid/4ab
         */
        public int nthUglyNumber(int n, int a, int b, int c) {
            int low = Math.min(a, Math.min(b, c));
            int high = n * low;
            while (low < high) {
                int mid = low + (high - low) / 2;
                // 本题的隐含条件是，找到第一个满足条件的值，
                // 因此checkLess满足时，即当前位置不满足时，需要在右边找
                if (checkLess(n, mid, a, b, c)) {
                    low = mid + 1;
                } else {
                    // 把==n和>n的分支合并了
                    high = mid;
                }
            }
            return low;
        }

        // // 优化：可以把lcm(a, b)、lcm(a, c)、lcm(b, c)、lcm(a, b, c)算一次存下来，以后每次直接使用
        private boolean checkLess(int n, int m, int a, int b, int c) {
            long uglyNum = (long) m / a + m / b + m / c - m / lcm(a, b) - m / lcm(b, c) - m / lcm(a, c) + m / lcm(a, lcm(b, c));
            return uglyNum < n;
        }

        // lowest common multiple，用num1*num2除其最大公约数即可
        // 证明：https://www.cnblogs.com/hellohebin/articles/15312502.html
        private long lcm(long num1, long num2) {
            return num1 / gcd(num1, num2) * num2;
        }

        // 辗转相除法求最大公约数，greatest common divisor
        private long gcd(long num1, long num2) {
            while (num2 > 0) {
                long tmp = num2;
                num2 = num1 % num2;
                num1 = tmp;
            }
            return num1;
        }


        // 丑数的传统做法，会超时，改用二分法
        public int nthUglyNumber1(int n, int a, int b, int c) {
            int i1 = 1, i2 = 1, i3 = 1;
            int ans = 0;
            long can1, can2, can3;
            for (int i = 1; i <= n; i++) {
                can1 = (long) a * i1;
                can2 = (long) b * i2;
                can3 = (long) c * i3;
                long min = Math.min(can1, Math.min(can2, can3));
                if (can1 == min) {
                    i1++;
                }
                if (can2 == min) {
                    i2++;
                }
                if (can3 == min) {
                    i3++;
                }
                if (i == n) {
                    ans = (int) min;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
