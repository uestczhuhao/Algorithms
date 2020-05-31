package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
 *
 * @author Zhu Hao <zhuhao3@xiaomi.com>
 * @date 20-4-15 下午7:52.
 * Description:
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class _78SubSets {

    public static void main(String[] args) {
        _78SubSets t = new _78SubSets();
        int[] nums = {1,2,3,4};

        System.out.println(t.subsets(nums));
    }

    public List<List<Integer>> subs = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }

        // 根据子集的长度循环
        findSubSets(0, new ArrayList<>(), nums);

        return subs;
    }

    /**
     * 回溯，从第1项开始做深搜，直到数组的结尾
     * 然后一轮轮回溯，带入不同的后缀，直到遍历完所有的可能
     * @param first 本轮的初始为止
     * @param currSubSet 目前为止的子集
     * @param nums 原数组
     */
    public void findSubSets(int first, List<Integer> currSubSet, int[] nums) {
        subs.add(new ArrayList<>(currSubSet));
        // 从first开始，到n-1为止
        for (int i = first; i < nums.length; i++) {
            currSubSet.add(nums[i]);
            findSubSets(i + 1, currSubSet, nums);
            currSubSet.remove(currSubSet.size()-1);
        }
    }
}
