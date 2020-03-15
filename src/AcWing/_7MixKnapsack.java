package AcWing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author mizhu
 * @date 20-3-15 下午4:23
 */
public class _7MixKnapsack {
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

    private static int maxValue(int itemNum, int totalVolume, int[] volumes, int[] weights, int[] nums) {
        if (itemNum <= 0 || totalVolume <= 0) {
            return 0;
        }
        int[] maxWeight = new int[totalVolume + 1];
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < itemNum; i++) {
            int num = nums[i];
            if (num < 0) {
                itemList.add(new Item(-1, volumes[i], weights[i]));
            } else if (num == 0) {
                itemList.add(new Item(0, volumes[i], weights[i]));
            } else {
                for (int k = 1; k <= num; k *= 2) {
                    num -= k;
                    itemList.add(new Item(-1, k * volumes[i], k * weights[i]));
                }
                if (num > 0) {
                    itemList.add(new Item(-1, num * volumes[i], num * weights[i]));
                }
            }
        }

        for (Item item : itemList) {
            // 完全背包问题，从前往后
            if (item.type == 0) {
                for (int j = item.volume; j <= totalVolume; j++) {
                    maxWeight[j] = Math.max(maxWeight[j], maxWeight[j - item.volume] + item.wright);
                }
            } else {
                // 01背包问题，从后往前
                for (int j = totalVolume; j >= item.volume; j--) {
                    maxWeight[j] = Math.max(maxWeight[j], maxWeight[j - item.volume] + item.wright);
                }
            }
        }

        return maxWeight[totalVolume];
    }

    private static class Item {
        Item(int type, int volume, int weight) {
            this.type = type;
            this.volume = volume;
            this.wright = weight;
        }

        int type;
        int volume;
        int wright;
    }
}
