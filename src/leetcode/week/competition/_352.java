package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class _352 {
    public static void main(String[] args) {
        int[] ns = {3, 5, 5, 5};
        _352 t = new _352();
//        System.out.println(t.longestAlternatingSubarray(ns, 5));
        System.out.println(t.findPrimePairs(15));
        System.out.println(t.findPrimePairs(2));
    }

    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int ans = 0;
        int left;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0 && nums[i] <= threshold) {
                left = i;
                ans = Math.max(ans, 1);
                for (int j = left + 1; j < nums.length && nums[j] <= threshold; j++) {
                    if (nums[j] % 2 == nums[j - 1] % 2) {
                        break;
                    }
                    ans = Math.max(j - i + 1, ans);
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> findPrimePairs(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> primes = new ArrayList<>();
        boolean[] arr = new boolean[n + 1];
        Arrays.fill(arr, true);
        /**
         * 任何合数都可以表示成一个素数乘以一个数，
         * 所以任何合数都有一个最小的质因数
         * 12=2x6=3x4 这里2、3都是质因数，用质因数2来筛12这个合数
         * 关键思想：每个数字都被它的最小质因数筛掉
         * *
         * 埃氏筛质数:又叫朴素筛法，求某个区间的质数个数时，从2开始，将每个数的倍数都筛掉，剩下的数就是质数。缺点：会对某个数重复筛。时间复杂度为O(nlogn)
         *
         * 线性筛法：对埃氏筛的优化，埃氏筛的任意一个数都被它的因数筛了一遍，实际上只需要筛一次就行，即每次只用最小质因子筛，不会被重复筛，时间复杂度为O(nloglogn)，非常接近线性。
         *
         * 两种筛法的相同点：都可以在筛选时找到质数的个数，求出区间内的所有质数，并且能求出每个数的最小质因子。
         *
         * 不同点：在10^6内，效率都差不多，但10^7时，线性筛明显比埃氏筛快一倍。
         *
         * https://blog.csdn.net/weixin_48898946/article/details/124051482
         */
        for (int i = 2; i <= n; i++) {
            if (arr[i]) {
                primes.add(i);
            }

            for (int j = 0; j < primes.size() && primes.get(j) <= n / i; j++) {
                arr[i * primes.get(j)] = false;
                // 保证每个合数只会被它的最小质因数筛去，减少冗余
                // 例如：12=2x6=3x4 ，当i=4时，由于 i%primes[0]=i%2=0，
                // 此时不能用i=4来筛12，因为后续i=6时会把12筛出来（用最小质因子2去乘），因此这里要break以避免重复
                // 即，如果primes.get(j)是i的因子，当遍历到j+1时，i*primes[j+1]可以分解成x*primes[j]*primes[j+1]的形式，而primes[j]比primes[j+1]小，
                // 后续肯定存在x*primes[j+1]，在primes[j]时把i*primes[j+1]划掉（可以带入i=4，primes[j]=2，primes[j+1]=3）
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }

        Set<Integer> set = new HashSet<>(primes);
        for (int i = 0; i < primes.size() && primes.get(i) <= n / 2; i++) {
            int value = primes.get(i);
            if (set.contains(n - value)) {
                List<Integer> res = new ArrayList<>();
                res.add(value);
                res.add(n - value);
                ans.add(res);
            }
        }
        return ans;
    }


}
