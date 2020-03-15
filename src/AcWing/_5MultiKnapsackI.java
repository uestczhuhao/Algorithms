package AcWing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author mizhu
 * @date 20-3-15 上午11:13
 */
public class _5MultiKnapsackI {
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
        System.out.println(maxValue1(itemNum, totalVolume, volumes, weights, nums));

    }

    /**
     * 01背包的扩展，多了
     *
     * @return
     */
    private static int maxValue(int itemNum, int totalVolume, int[] volumes, int[] weights, int[] nums) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }
        int[] maxWeight = new int[totalVolume + 1];
        for (int i = 0; i < itemNum; i++) {
            for (int j = totalVolume; j >= 0; j--) {
                for (int k = 1; k * volumes[i] <= j && k <= nums[i]; k++) {
                    maxWeight[j] = Math.max(maxWeight[j], maxWeight[j - k * volumes[i]] + k * weights[i]);
                }
            }
        }
        return maxWeight[totalVolume];
    }

    private static int maxValue1(int itemNum, int totalVolume, int[] volumes, int[] weights, int[] nums) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }
        int[] maxWeight = new int[totalVolume + 1];
        List<Item> itemList = new ArrayList<>();
        // 二进制分法，把num个物品分为1 2 4 8 ... 个，
        // 每个物品的体积为k * volume[i]，价值为k * weights[i]
        // 从n降低为log(n)
        for (int i = 0; i < itemNum; i++) {
            int num = nums[i];
            for (int k = 1; k <= num; k *= 2) {
                num -= k;
                Item item = new Item(k *volumes[i], k* weights[i]);
                itemList.add(item);
            }
            if (num > 0){
                itemList.add(new Item(num * volumes[i], num * weights[i]));
            }
        }
        for (Item item: itemList) {
            for (int j = totalVolume; j >= 0 && j>= item.volume; j--) {
                maxWeight[j] = Math.max(maxWeight[j], maxWeight[j - item.volume] + item.wright);
            }
        }
        return maxWeight[totalVolume];
    }

    private static class Item {
        Item(int volume, int weight) {
            this.volume = volume;
            this.wright = weight;
        }
        int volume;
        int wright;
    }
}
