package LeetCode;

import java.util.*;

/**
 * @author mizhu
 * @date 20-1-31 下午3:56
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
public class _347TopKFrequentElements {
    public static void main(String[] args) {
//        int[] nums = {1,1,1,2,2,3};
        int[] nums = {1};
        System.out.println(topKFrequent(nums,1));
    }

    /**
     * 先遍历一次数组，将元素 -> 出现次数放入map
     * 再遍历map，找出前k个
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k> nums.length) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> numFreqMap = new HashMap<>();
        for (int num : nums) {
            numFreqMap.put(num, numFreqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        Map<Integer, Integer> assistMap = new HashMap<>(numFreqMap);
        for (Map.Entry<Integer, Integer> numFre : numFreqMap.entrySet()) {
            heap.add(numFre);
            while (heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        heap.forEach(entry -> result.add(entry.getKey()));
        return result;
    }
}
