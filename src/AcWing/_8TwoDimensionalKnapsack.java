package AcWing;

import java.util.Scanner;

/**
 * @author mizhu
 * @date 20-3-16 下午11:17
 */
public class _8TwoDimensionalKnapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int itemNum = 0, totalVolume = 0, totalMass;
        itemNum = scanner.nextInt();
        totalVolume = scanner.nextInt();
        totalMass = scanner.nextInt();

        int[] volumes = new int[itemNum];
        int[] mass = new int[itemNum];
        int[] weights = new int[itemNum];
        int i = 0;
        while (scanner.hasNext()) {
            volumes[i] = scanner.nextInt();
            mass[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
            i++;
        }

        if (itemNum <= 0 || totalMass <= 0 || totalVolume <= 0) {
            return;
        }

        int[][] maxWeight = new int[totalVolume + 1][totalMass + 1];
        for (i = 0; i < itemNum; i++) {
            for (int j = totalVolume; j>= volumes[i];j--) {
                for (int k = totalMass; k>= mass[i]; k--) {
                    maxWeight[j][k] = Math.max(maxWeight[j][k],
                            maxWeight[j - volumes[i]][k - mass[i]] + weights[i]);
                }
            }
        }
        System.out.println(maxWeight[totalVolume][totalMass]);
    }
}
