package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-1-20 下午5:22.
 * Description:
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class _560SubArrayKNum {
    public static void main(String[] args) {
//        int[] nums = {1,2,3};
        int[] nums = {0,0,0,0,0,0,0,0,0,0};
        System.out.println(subarraySum(nums, 3));
    }

    public static int subarraySum(int[] nums, int k) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int inputLength = nums.length;
        int[] sumArrayFromHead = new int[inputLength];
        sumArrayFromHead[0] = nums[0];

        // 构建一个sum数组，存放从0~i的和
        for (int i = 1; i < inputLength; i++) {
            sumArrayFromHead[i] = sumArrayFromHead[i - 1] + nums[i];
        }

        int targetNum = 0;
        // 构建一个map，key为sum，value为相同sum的数量
        // 如[1,1,1] 构建的sum 数组为[1, 2, 3]，map为 1->1, 2->1, 3->1
        Map<Integer, Integer> sumNumMap = new HashMap<>();
        for (int i = 0; i < inputLength; i++) {
            int iThSum = sumArrayFromHead[i];
            // 补偿
            if (iThSum == k) {
                targetNum++;
            }
            // 遍历至i时的sum，加上满足条件的数量
            targetNum += sumNumMap.getOrDefault(iThSum - k, 0);

            // 先处理第i个sum值，再将其放入map
            if (sumNumMap.containsKey(iThSum)) {
                sumNumMap.put(iThSum, sumNumMap.get(iThSum) + 1);
            } else {
                sumNumMap.put(iThSum, 1);
            }
        }
        return targetNum;
    }
}
