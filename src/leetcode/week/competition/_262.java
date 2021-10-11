package leetcode.week.competition;

import java.util.*;

public class _262 {
    public static void main(String[] args) {
        _262 t = new _262();
        int[] nums1 = {3,1};
        int[] nums2 = {2,3};
        int[] nums3 = {1,2};

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

class StockPrice {
    Map<Integer,Integer> timePriceMap = new HashMap<>();
    int curTime = 0;
    int maxPrice = Integer.MIN_VALUE;
    int minPrice = Integer.MAX_VALUE;
    public StockPrice() {}

    public void update(int timestamp, int price) {
        if (timestamp > curTime) {
            curTime = timestamp;
        }

        if (timePriceMap.containsKey(timestamp)) {
            int ordinaryPrice = timePriceMap.get(timestamp);
            if (maxPrice == ordinaryPrice) {
                maxPrice = Integer.MIN_VALUE;
            }
            if (minPrice == ordinaryPrice) {
                minPrice = Integer.MAX_VALUE;
            }
        }
        if (maxPrice != Integer.MIN_VALUE) {
            maxPrice = Math.max(maxPrice, price);
        }

        if (minPrice != Integer.MAX_VALUE) {
            minPrice = Math.min(minPrice, price);
        }
        timePriceMap.put(timestamp, price);
    }

    public int current() {
        return timePriceMap.get(curTime);
    }

    public int maximum() {
        if (maxPrice == Integer.MIN_VALUE) {
            Set<Integer> set = new HashSet<>(timePriceMap.values());
            for (int i : set) {
                maxPrice = Math.max(i, maxPrice);
            }
        }
        return maxPrice;
    }

    public int minimum() {
        if (minPrice == Integer.MAX_VALUE) {
            Set<Integer> set = new HashSet<>(timePriceMap.values());
            for (int i : set) {
                minPrice = Math.min(i, minPrice);
            }
        }
        return minPrice;
    }


}
