package poj;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author mizhu
 * @date 2020/3/29 16:24
 * 根据月赛的名称，我们可以写出数据地址。比如08年一月的月赛即是：http://contest.usaco.org/JAN08  这里要注意区分大小写。
 * <p>
 * 题意：
 * <p>
 * 在长为L(<=1000000)的草地（可看成线段）上装喷水头，喷射是以这个喷水头为中心，喷水头的喷洒半径是可调节的，调节范围为[a,b]。要求草地的每个点被且只被一个喷水头覆盖，并且有些连续区间必须被某一个喷水头覆盖而不能由多个喷头分段完全覆盖，求喷水头的最小数
 * <p>
 * 这道题，想到是DP，但是阶段与状态刚开始没搞明白。想到将每个区间当作一个阶段，这样过去。但是问题依旧很多，没有摆脱对前一个点位置的限定。其实会看题意。是要求完全覆盖，多出的洒水范围是不允许的，那么从0到len中应该是完全覆盖。那么，
 * <p>
 * 定义dp[i]为覆盖[0,i]区间所需的的最小喷头数。dp[i]=min( dp[j] )+1,  i-2*b<=j<=i-2*a.   因为一个喷头可以解决的范围至多是2*a~2*b，那么将其之前那些用阶段分开。前面 i-j 个都放好了...然后再在 j中放一个使得这一段全被覆盖。
 * <p>
 * 因为一个喷头解决的区间必然是偶数，所以跳过所有的奇数。
 * <p>
 * 还有就是奶牛喜欢的区域只能用一个喷头。那么奶牛区间就不会被完全覆盖（完全覆盖了就将区间分开了），跳过去。
 */
public class _2373DividingThePath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cowNum = scanner.nextInt();
        int totalLength = scanner.nextInt();
        int minCover = scanner.nextInt();
        int maxCover = scanner.nextInt();

        Cow[] cows = new Cow[cowNum];
        for (int i = 0; i < cowNum; i++) {
            cows[i] = new Cow(scanner.nextInt(), scanner.nextInt());
        }

        System.out.println(minSprinklers(totalLength, minCover, maxCover, cows));
    }

    /*
    输入：
    2 8
    1 2
    6 7
    3 6
     */

    /**
     * 解答：https://suprit.blog.csdn.net/article/details/83826932
     */
    private static int minSprinklers(int totalLength, int minCover, int maxCover, Cow[] cows) {
        // totalLength 必须为偶数
        if (totalLength < 0 || (totalLength & 1) == 1) {
            return -1;
        }

        PriorityQueue<Helper> helpers = new PriorityQueue<>();
        int INF = Integer.MAX_VALUE - 2 * maxCover;
        // dp[i] 代表从0-i所需要的最小喷头数
        int[] dp = new int[totalLength + 1];
        Arrays.fill(dp, INF);
        // 标记某个下标是否在奶牛的感兴趣范围之内
        boolean[] cowRange = new boolean[totalLength + 1];

        for (Cow cow : cows) {
            for (int j = cow.start + 1; j < cow.end; j++) {
                cowRange[j] = true;
            }
        }

        // base case，在 2*minCover ~ 2 * maxCover范围内，最少的可能性是1种
        for (int i = 2 * minCover; i <= 2 * maxCover; i += 2) {
            if (!cowRange[i]) {
                dp[i] = 1;
                if (i <= 2 * maxCover + 2 - 2 * minCover) {
                    helpers.add(new Helper(i, 1));
                }
            }
        }

        for (int i = 2 * maxCover + 2; i <= totalLength; i += 2) {
            if (!cowRange[i]) {
                while (!helpers.isEmpty()) {
                    Helper current = helpers.peek();
                    if (current.index < i - 2 * maxCover) {
                        helpers.poll();
                    } else {
                        break;
                    }
                }
                if (!helpers.isEmpty()) {
                    dp[i] = helpers.peek().spNum + 1;
                }
            }
            if (dp[i - 2*minCover + 2] != INF) {
                helpers.add(new Helper(i - 2*minCover + 2, dp[i - 2*minCover + 2]));
            }
        }

        return dp[totalLength] == INF ? -1 : dp[totalLength];
    }

    /**
     * 原始方法，会超时
     */
    private static int minSprinklers1(int totalLength, int minCover, int maxCover, Cow[] cows) {
        // totalLength 必须为偶数
        if (totalLength < 0 || (totalLength & 1) == 1) {
            return -1;
        }

        int INF = Integer.MAX_VALUE - 2 * maxCover;
        // dp[i] 代表从0-i所需要的最小喷头数
        int[] dp = new int[totalLength + 1];
        Arrays.fill(dp, INF);
        // 标记某个下标是否在奶牛的感兴趣范围之内
        boolean[] cowRange = new boolean[totalLength + 1];

        for (Cow cow : cows) {
            for (int j = cow.start + 1; j < cow.end; j++) {
                cowRange[j] = true;
            }
        }

        // base case，在 2*minCover ~ 2 * maxCover范围内，最少的可能性是1种
        for (int i = 2 * minCover; i <= 2 * maxCover; i += 2) {
            if (!cowRange[i]) {
                dp[i] = 1;
            }
        }

        for (int i = 2 * maxCover + 2; i <= totalLength; i += 2) {
            if (!cowRange[i]) {
                for (int j = i - 2 * maxCover; j <= i - 2 * minCover; j++) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[totalLength] == INF ? -1 : dp[totalLength];
    }

    private static class Cow {
        int start;
        int end;

        public Cow(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    private static class Helper implements Comparable {
        int index;
        int spNum;

        @Override
        public int compareTo(Object o) {
            Helper other = (Helper) o;
            return this.spNum - other.spNum;
        }

        public Helper(int index, int spNum) {
            this.index = index;
            this.spNum = spNum;
        }

        public Helper() {
        }
    }
}
