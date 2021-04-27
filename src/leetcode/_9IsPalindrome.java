package leetcode;

public class _9IsPalindrome {
    public boolean isPalindrome1(int x) {
        if (x < 0 || (x % 10 ==0 && x !=0))
            return false;

        int tmp = x,figures = 0;
        while (tmp > 0){
            tmp = tmp/10;
            figures++;
        }

        int pow = (int) Math.pow(10, figures >> 1);
        int upper = x / pow, lower = x % pow;
        if (figures % 2 != 0 ){
            upper /= 10;
        }
        int lowerRev = 0,i=1;
        while (i++ <= (figures>>1)){
            lowerRev = lowerRev*10 + lower%10;
            lower /=10;
        }
        return upper == lowerRev;
    }

    /**
     * 反转x的后面一半，判断其前面一半和右边一半是否相同
     */
    public boolean isPalindrome(int x) {
        // 小于0的数和最后一位为0的数（0除外）不可能为回文，因为第一位不为回文
        if (x < 0 || (x %10 == 0 && x!= 0)) {
            return false;
        }

        int reverse = 0;
        // 反转直到x 不再大于reverse，两种情况
        // 1 若x的位数为偶数，则退出时x 和reverse位数相等
        // 2 若x的位数为奇数，退出时reverse位数比x多一位
        while (x > reverse) {
            reverse = 10 * reverse + x % 10;
            x = x / 10;
        }

        return x == reverse || x == reverse / 10;
    }

        public static void main(String[] args) {
        _9IsPalindrome test = new _9IsPalindrome();
            System.out.println(test.isPalindrome(100001));
//        System.out.println(5>>1);
//        System.out.println(4>>1);
    }

}
