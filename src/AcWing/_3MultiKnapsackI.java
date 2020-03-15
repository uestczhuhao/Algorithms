package AcWing;

import java.util.Scanner;

/**
 * @author mizhu
 * @date 20-3-15 上午11:13
 */
public class _3MultiKnapsackI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int itemNum = 0, totalVolume = 0;
        itemNum = scanner.nextInt();
        totalVolume = scanner.nextInt();

        int[] volumes = new int[itemNum];
        int[] weights = new int[itemNum];
        int[] nums = new int[itemNum];
        int i = 0;
        while (scanner.hasNext()) {
            volumes[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
            nums[i] = scanner.nextInt();
            i++;
        }
        System.out.println(maxValue(itemNum, totalVolume, volumes, weights, nums));

    }

    /**
     * 01背包的扩展，多了
     * @return
     */
    private static int maxValue(int itemNum, int totalVolume, int[] volumes, int[] weights, int[] nums) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }
        int[] maxWeight = new int[totalVolume + 1];
        for (int i = 0; i < itemNum; i++) {
            for (int j = totalVolume; j >= 0; j--) {
                for (int k = 1; k * volumes[i] <= j && k <= nums[i]; k ++) {
                    maxWeight[j] = Math.max(maxWeight[j], maxWeight[j - k * volumes[i]] + k * weights[i]);
                }
            }
        }
        return maxWeight[totalVolume];
    }
}
