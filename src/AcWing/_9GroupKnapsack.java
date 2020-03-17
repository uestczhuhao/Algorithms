package AcWing;

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
    }
}
