package AcWing;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author mizhu
 * @date 2020/3/22 23:26
 */
public class _11KnapsackNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int itemNum = 0, totalVloume = 0;
        itemNum = scanner.nextInt();
        totalVloume = scanner.nextInt();
        int[] volumes = new int[itemNum];
        int[] weights = new int[itemNum];
        int i = 0;
        while (scanner.hasNext()) {
            volumes[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
            i++;
        }

        System.out.println(maxValueNum(itemNum, totalVloume, volumes, weights));
    }

    static int maxValueNum(int itemNum, int totalVolume, int[] volumes, int[] weights) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }

        // 代表容积为j时的最大价值
        int[] maxWeight = new int[totalVolume + 1];
        // 代表容积为j时的最优方案数
        int[] planNum = new int[totalVolume + 1];
        Arrays.fill(planNum, 1);
        for (int i = 0; i < itemNum; i++) {
            for (int j = totalVolume; j >= volumes[i]; j--) {
                int ithMaxWeight = maxWeight[j - volumes[i]] + weights[i];
                // 第i件物品装进背包，则方案数与前i-1时相同
                if (ithMaxWeight > maxWeight[j]) {
                    planNum[j] = planNum[j - volumes[i]];
                    maxWeight[j] = ithMaxWeight;
                } else if (ithMaxWeight == maxWeight[j]) {
                    // 第i件物品装/不装均可，则方案数增加，增加的数量即为前i-1都装进去
                    planNum[j] += planNum[j - volumes[i]];
                    if (planNum[j] > Math.pow(10, 9) + 7) {
                        planNum[j] -= Math.pow(10, 9) + 7;
                    }
                }
                // 隐含都else，装进i件物品反而达不到最优，则忽略
            }
        }

        return planNum[totalVolume];
    }
}
