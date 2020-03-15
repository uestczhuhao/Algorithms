package AcWing;

import java.util.Scanner;

/**
 * @author mizhu
 * @date 20-3-15 上午11:02
 */
public class _1ZeroOneKnapsack {
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
        int i = 0;
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
        System.out.println(maxVlue(itemNum, totalVloume, volumes, weights));
    }

    private static int maxVlue(int itemNum, int totalVolume, int[] volumes, int[] weights) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }

        int[] maxWeight = new int[totalVolume + 1];
        for (int i = 0; i < itemNum; i++) {
            for (int j = totalVolume; j >= volumes[i]; j--) {
                maxWeight[j] = Math.max(maxWeight[j], maxWeight[j - volumes[i]] + weights[i]);
            }
        }

        return maxWeight[totalVolume];
    }
}
