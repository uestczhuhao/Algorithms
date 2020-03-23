package AcWing;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author mizhu
 * @date 2020/3/23 16:51
 */
public class _12SpecialKnapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int itemNum = 0, totalVolume = 0;
        itemNum = scanner.nextInt();
        totalVolume = scanner.nextInt();
        int[] volumes = new int[itemNum];
        int[] weights = new int[itemNum];
        int i = 0;
        while (scanner.hasNext()) {
            volumes[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
            i++;
        }

        minLexicographic(itemNum, totalVolume, volumes, weights);
    }

    private static void minLexicographic(int itemNum, int totalVolume, int[] volumes, int[] weights) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return;
        }

        // 0/1背包，maxWeight[i][j]代表选取后n-i+1个物品（从i到n），容量为j时都最大价值
        int[][] maxWeight = new int[itemNum + 1][totalVolume + 1];
        for (int i = itemNum - 1; i >= 0; i--) {
            for (int j = totalVolume; j >= 0; j--) {
                // TODO：此处待想明白
                maxWeight[i][j] = maxWeight[i+1][j];
                if (j >= volumes[i]) {
                    maxWeight[i][j] = Math.max(maxWeight[i][j], maxWeight[i + 1][j - volumes[i]] + weights[i]);
                }
            }
        }

//        print(maxWeight);

        int curVolume = totalVolume;
        for (int i = 0; i < itemNum; i++) {
            if (curVolume <= 0) {
                return;
            }
            // maxWeight[i][curVolume] == maxWeight[i + 1][curVolume - volumes[i]] + weights[i]
            // 表达式成立第前提是：选择了第i件，即i第状态由i+1转移而来
            if (curVolume >= volumes[i]
                    && maxWeight[i][curVolume] == maxWeight[i + 1][curVolume - volumes[i]] + weights[i]) {
                System.out.print(i + 1);
                System.out.print(" ");
                curVolume -= volumes[i];
            }
        }

    }

    static void print(int[][] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            System.out.println(Arrays.toString(a[i]));
        }
    }

}
