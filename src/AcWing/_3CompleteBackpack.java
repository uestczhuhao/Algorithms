package AcWing;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author zhuhao3@xiaomi.com
 * @date 2020/3/12 22:43
 * <p>
 * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
 * <p>
 * 第 i 种物品的体积是 vi，价值是 wi。
 * <p>
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 * <p>
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 * <p>
 * 接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 种物品的体积和价值。
 * <p>
 * 输出格式
 * 输出一个整数，表示最大价值。
 * <p>
 * 数据范围
 * 0<N,V≤1000
 * 0<vi,wi≤1000
 */
public class _3CompleteBackpack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int itemNum = 0, totalVloume = 0;
        if (scanner.hasNextLine()) {
            String firstLine = scanner.nextLine();
            String[] totalInput = firstLine.split(" ");
            if (totalInput.length != 2) {
                return;
            }
            itemNum = Integer.parseInt(totalInput[0].trim());
            totalVloume = Integer.parseInt(totalInput[1].trim());
        }
        int[] volumes = new int[itemNum];
        int[] weights = new int[itemNum];
        int i=0;
        while (scanner.hasNextLine()) {
            String others = scanner.nextLine();
            String[] otherInputs = others.split(" ");
            if (otherInputs.length != 2) {
                return;
            }
            volumes[i] = Integer.parseInt(otherInputs[0]);
            weights[i] = Integer.parseInt(otherInputs[1]);
            i++;
        }
//        System.out.println(Arrays.toString(volumes));
//        System.out.println(Arrays.toString(weights));

        System.out.println(maxValue(itemNum, totalVloume, volumes, weights));
    }

    /**
     * 完全背包问题，dp[i][j] 代表前i件物品中，总体积为j时的最大价值
     * 二维压缩至一维，保留j，当遍历至i时，dp[j] 代表此时背包容量为j
     *
     * @param itemNum     物品数量
     * @param totalVolume 背包总容量
     * @param volumes     物品的体积
     * @param weights     物品的价值
     * @return
     */
    public static int maxValue(int itemNum, int totalVolume, int[] volumes, int[] weights) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }

        int[] dp = new int[totalVolume + 1];
        dp[0] = 0;
        for (int i = 0; i < itemNum; i++) {
            int volume = volumes[i];
            int weight = weights[i];
            for (int j= volume; j<=totalVolume;j ++){
                // dp[j] 代表不放第i件物品，前i-1件物品体积为j时最大价值
                // dp[j-volume] + weight 代表把i放入背包，则前i-1件物品的体积则为j-volume
                dp[j] = Math.max(dp[j], dp[j-volume] + weight);
            }
        }

        return dp[totalVolume];
    }
}
