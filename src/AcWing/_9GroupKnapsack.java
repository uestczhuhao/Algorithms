package AcWing;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author mizhu
 * @date 20-3-17 上午11:30
 */
public class _9GroupKnapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int itemNum = 0, totalVolume = 0, totalMass;
        itemNum = scanner.nextInt();
        totalVolume = scanner.nextInt();

        int[][] volumes = new int[itemNum][];
        int[][] weights = new int[itemNum][];
        int i = 0;
        while (scanner.hasNext()) {
            int group = scanner.nextInt();
            volumes[i] = new int[group];
            weights[i] = new int[group];
            for (int j = 0; j < group; j++) {
                volumes[i][j] = scanner.nextInt();
                weights[i][j] = scanner.nextInt();
            }
            i++;
        }

        System.out.println(maxValue(itemNum, totalVolume, volumes, weights));
    }

    private static int maxValue(int itemNum, int totalVolume, int[][] volumes, int[][] weights) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }

        int[] maxWeight = new int[totalVolume + 1];
        for (int i = 0; i < itemNum; i++) {
            for (int j = totalVolume; j >= 0; j--) {
                int groupItemNum = volumes[i].length;
                // 组内每个物品进行循环，在组内选一个能使最终价值最大的
                for (int k = 0; k < groupItemNum; k++) {
                    if (j >= volumes[i][k]) {
                        maxWeight[j] = Math.max(maxWeight[j], maxWeight[j - volumes[i][k]] + weights[i][k]);
                    }
                }
            }
        }
        return maxWeight[totalVolume];
    }
}
