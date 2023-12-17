package leetcode;

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
//        int[] nums = {1, 1, 1, 1, 1, 3};
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
//        int[] nums = {1};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numFreqMap = new HashMap<>();
        for (int num : nums) {
            numFreqMap.put(num, numFreqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] freNumList = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> numFre : numFreqMap.entrySet()) {
            int num = numFre.getKey();
            int fre = numFre.getValue();
            if (freNumList[fre] == null) {
                freNumList[fre] = new ArrayList<>();
            }
            freNumList[fre].add(num);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = nums.length; i >= 0 && ans.size() < k; i--) {
            if (freNumList[i] == null) {
                continue;
            }
            ans.addAll(freNumList[i]);
        }

//        return ans.toArray(new Integer[0]);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 先遍历一次数组，将元素 -> 出现次数放入map
     * 再将map放入优先队列中（小的在前，因为队列先进先出），保留前k项，就是所求的结果
     */
    public static List<Integer> topKFrequent1(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k > nums.length) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> numFreqMap = new HashMap<>();
        for (int num : nums) {
            numFreqMap.put(num, numFreqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
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
