package leetcode;

public class _29DivideTwoIntegers {
    public static void main(String[] args) {
//        System.out.println(Integer.MIN_VALUE);
        System.out.println(divide(Integer.MIN_VALUE,-1));
    }

    static int divide(int dividend, int divisor){

        long aDivided =  Math.abs((long) dividend);
        long aDivisor= Math.abs((long) divisor);

         if (dividend == 0 || aDivided < aDivisor){
             return 0;
         }
         int sign = 1;
         if ((dividend >0 && divisor < 0) || (dividend < 0 && divisor > 0)){
            sign = -1;
         }
         long res = doDivide(aDivided,aDivisor);
         if (res > Integer.MAX_VALUE){
             return sign ==1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
         }

         return (sign == 1) ? (int) res : (int) -res;
    }

    /**
     * 递归，2*x + 2^2*x + ... + 2^n*x <= y
     * 先找到最大的n，再依次找到n-1，n-2，最后求和即可
     * @return
     */
    static private long doDivide(long aDividend, long aDivisor){
        if (aDividend < aDivisor){
            return 0;
        }

        long sum = aDivisor;
        long res = 1;
        while ((sum + sum) <= aDividend){
            sum += sum;
            res += res;
        }

        return res + doDivide(aDividend - sum, aDivisor);
    }

    public static int divide1(int dividend, int divisor) {
        //Reduce the problem to positive long integer to make it easier.
        //Use long to avoid integer overflow cases.
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
            sign = -1;
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        //Take care the edge cases.
        if (ldivisor == 0) return Integer.MAX_VALUE;
        if ((ldividend == 0) || (ldividend < ldivisor))	return 0;

        long lans = ldivide(ldividend, ldivisor);

        int ans;
        if (lans > Integer.MAX_VALUE){ //Handle overflow.
            ans = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = (int) (sign * lans);
        }
        return ans;
    }

    private static long ldivide(long ldividend, long ldivisor) {
        // Recursion exit condition
        if (ldividend < ldivisor) return 0;

        //  Find the largest multiple so that (divisor * multiple <= dividend),
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        long sum = ldivisor;
        long multiple = 1;
        while ((sum+sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        //Look for additional value for the multiple from the reminder (dividend - sum) recursively.
        return multiple + ldivide(ldividend - sum, ldivisor);
    }
}
