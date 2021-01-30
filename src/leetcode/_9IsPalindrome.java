package leetcode;

public class _9IsPalindrome {
    public boolean isPalindrome(int x) {
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

    public static void main(String[] args) {
        _9IsPalindrome test = new _9IsPalindrome();
        test.isPalindrome(100001);
//        System.out.println(5>>1);
//        System.out.println(4>>1);
    }

}
