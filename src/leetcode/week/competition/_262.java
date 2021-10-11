package leetcode.week.competition;

import java.util.*;

public class _262 {
    public static void main(String[] args) {
        _262 t = new _262();
        int[] nums1 = {3, 1};
        int[] nums2 = {2, 3};
        int[] nums3 = {1, 2};

//        ["StockPrice","update","maximum","current","minimum","maximum","maximum","maximum","minimum","minimum","maximum","update","maximum","minimum","update","maximum","minimum","current","maximum","update","minimum","maximum","update","maximum","maximum","current","update","current","minimum","update","update","minimum","minimum","update","current","update","maximum","update","minimum"]
//[[],[38,2308],[],[],[],[],[],[],[],[],[],[47,7876],[],[],[58,1866],[],[],[],[],[43,121],[],[],[40,5339],[],[],[],[32,5339],[],[],[43,6414],[49,9369],[],[],[36,3192],[],[48,1006],[],[53,8013],[]];
//        [null,null,2308,2308,2308,2308,2308,2308,2308,2308,2308,null,2308,2308,null,2308,2308,1866,2308,null,2308,2308,null,2308,2308,1866,null,1866,2308,null,null,2308,2308,null,1866,null,2308,null,2308];
//        [null,null,2308,2308,2308,2308,2308,2308,2308,2308,2308,null,7876,2308,null,7876,1866,1866,7876,null,121,7876,null,7876,7876,1866,null,1866,121,null,null,1866,1866,null,1866,null,9369,null,1006]

//        System.out.println(t.twoOutOfThree(nums1, nums2, nums3));
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(38, 2308);
        System.out.println(stockPrice.maximum());
        stockPrice.update(47, 7876);
        System.out.println(stockPrice.maximum());
        System.out.println(stockPrice.minimum());

//        stockPrice.update(58, 1866);
//        stockPrice.update(43, 121);
//        stockPrice.update(40, 5339);
//        stockPrice.update(32, 5339);
//        stockPrice.update(43, 6414);
//        stockPrice.update(49, 9369);
//        System.out.println(stockPrice.minimum());
//
//        stockPrice.update(36, 3192);
//        stockPrice.update(48, 1006);
//        stockPrice.update(53, 8013);

    }


    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        Set<Integer> ans = new HashSet<>();

        for (int i : nums2) {
            if (set1.contains(i)) {
                ans.add(i);
            }
            set2.add(i);
        }

        for (int i : nums3) {
            if (set1.contains(i) || set2.contains(i)) {
                ans.add(i);
            }
        }

        return new ArrayList<>(ans);
    }
}

/**
 * 采用一个timePriceMap和一个priceTimeMap（有序map，同时解决最大值和最小值问题）
 * 注意：一个大顶堆和一个小顶堆的解法会超时
 */
class StockPrice {
    Map<Integer, Integer> timePriceMap = new HashMap<>();
    int curTime = 0;
    TreeMap<Integer, Integer> priceTimeMap = new TreeMap<>((a, b) -> b - a);

    public StockPrice() {
    }

    public void update(int timestamp, int price) {
        if (timestamp > curTime) {
            curTime = timestamp;
        }

        if (timePriceMap.containsKey(timestamp)) {
            int ordinaryPrice = timePriceMap.get(timestamp);
            if (priceTimeMap.containsKey(ordinaryPrice)) {
                priceTimeMap.put(ordinaryPrice, priceTimeMap.get(ordinaryPrice) - 1);
                if (priceTimeMap.get(ordinaryPrice) == 0) {
                    priceTimeMap.remove(ordinaryPrice);
                }
            }
        }

        priceTimeMap.put(price, priceTimeMap.getOrDefault(price, 0) + 1);
        timePriceMap.put(timestamp, price);
    }

    public int current() {
        return timePriceMap.get(curTime);
    }

    public int maximum() {
        return priceTimeMap.firstKey();
    }

    public int minimum() {
        return priceTimeMap.lastKey();
    }


}
