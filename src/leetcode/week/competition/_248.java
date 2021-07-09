package leetcode.week.competition;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author mizhu
 * @date 2021/7/9 15:00
 */
public class _248 {
    public static void main(String[] args) {
        _248 t = new _248();
        int[] nums = {0, 2, 1, 5, 3, 4};
//        System.out.println(Arrays.toString(t.buildArray(nums)));
        int[] dis = {1, 1, 2, 3};
        int[] speed = {1, 1, 1, 1};
//        System.out.println(t.eliminateMaximum(dis, speed));
//        System.out.println(t.countGoodNumbers(4));
        System.out.println(t.countGoodNumbers(806166225460393L));
    }

    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        PriorityQueue<Double> queue = new PriorityQueue<>();
        for (int i = 0; i < dist.length; i++) {
            queue.offer(1.0 * dist[i] / speed[i]);
        }

        queue.poll();
        int ans = 1, index = 1;
        while (index < dist.length && !queue.isEmpty()) {
            if (queue.poll() <= index++) {
                break;
            }
            ans++;
        }

        return ans;
    }

    public int countGoodNumbers(long n) {
        long ans;
        if (n % 2 == 0) {
            ans = 1;
        } else {
            ans = 5;
            n--;
        }

        n /= 2;

        while (n >= 1) {
            long tmpAns = 20;
            long i = 2;
            for (; i < n; i *= 2) {
                tmpAns = (tmpAns * tmpAns) % 1000000007;
            }

            n -= i / 2;
            ans = (ans * tmpAns) % 1000000007;
        }


        return (int) ans;
    }

}
