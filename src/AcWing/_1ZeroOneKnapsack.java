package AcWing;

import java.util.Arrays;

/**
 * @author mizhu
 * @date 20-3-15 上午11:02
 */
public class _1ZeroOneKnapsack {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int itemNum = 0, totalVloume = 0;
//        if (scanner.hasNextLine()) {
//            String firstLine = scanner.nextLine();
//            String[] totalInput = firstLine.split(" ");
//            if (totalInput.length != 2) {
//                return;
//            }
//            itemNum = Integer.parseInt(totalInput[0].trim());
//            totalVloume = Integer.parseInt(totalInput[1].trim());
//        }
//        int[] volumes = new int[itemNum];
//        int[] weights = new int[itemNum];
//        int i = 0;
//        while (scanner.hasNextLine()) {
//            String others = scanner.nextLine();
//            String[] otherInputs = others.split(" ");
//            if (otherInputs.length != 2) {
//                return;
//            }
//            volumes[i] = Integer.parseInt(otherInputs[0]);
//            weights[i] = Integer.parseInt(otherInputs[1]);
//            i++;
//        }
//        System.out.println(maxValue(itemNum, totalVloume, volumes, weights));
//    }

    public static void main(String[] args) {
        int itemNum = 3;
        int totalVolume = 4;
        int[] volumes = {3, 1, 1};
        int[] weights = {20, 15, 35};
        System.out.println(maxValue(itemNum, totalVolume, volumes, weights));
        System.out.println(maxValue1(itemNum, totalVolume, volumes, weights));
    }

    private static int maxValue(int itemNum, int totalVolume, int[] volumes, int[] weights) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }

        int[] maxWeight = new int[totalVolume + 1];
        for (int i = 0; i < itemNum; i++) {
            for (int j = totalVolume; j >= volumes[i]; j--) {
                maxWeight[j] = Math.max(maxWeight[j], maxWeight[j - volumes[i]] + weights[i]);
            }
            System.out.println(Arrays.toString(maxWeight));
        }

        return maxWeight[totalVolume];
    }

    private static int maxValue1(int itemNum, int totalVolume, int[] volumes, int[] weights) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }

        int[][] maxWeight = new int[itemNum + 1][totalVolume + 1];
        for (int i = 1; i <= itemNum; i++) {
            for (int j = volumes[i - 1]; j <= totalVolume; j++) {
//            for (int j = totalVolume; j >= volumes[i - 1]; j--) {
                maxWeight[i][j] = Math.max(maxWeight[i - 1][j], maxWeight[i - 1][j - volumes[i - 1]] + weights[i - 1]);
            }
        }

        System.out.println(Arrays.deepToString(maxWeight));
        return maxWeight[itemNum][totalVolume];
    }
}
