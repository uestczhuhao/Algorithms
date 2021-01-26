package LeetCode;

/**
 * @author mizhu
 * @date 2021/1/26 23:08
 */
public class _70ClimbStairs {
    public int climbStairs(int n) {
        int pre;
        int first = 0;
        int second = 1;
        for (int i = 1; i <= n; i++) {
            pre = first;
            first = second;
            second = pre + first;
        }

        return second;
    }


}
