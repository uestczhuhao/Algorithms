package leetcode;

import java.util.Arrays;

public class _26RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] arr = {0,0,1,1,2,2,3,3,4,4};
        int[] arr1 = {0,1,1,2,2,3};
        System.out.println(removeDuplicates1(arr));
        System.out.println(Arrays.toString(arr));
    }

    // O(n^2)解法，需要优化
    public static int removeDuplicates(int[] nums) {
        if (nums == null){
            return 0;
        }
        if (nums.length == 1 || nums.length == 0){
            return nums.length;
        }

        int lastVisited = nums[0];
        for (int index = 1;index < nums.length; index ++){
            if (nums[index] <= lastVisited){
                // 可以优化的点：不必每次都遍历，题目要求只找到第一个即可
                for (int j = index+1;j < nums.length; j++){
                    if (nums[j] > lastVisited){
                        nums[index] = nums[j];
                        break;
                    }
                }
            }
            lastVisited = nums[index];
        }

        for (int i = 1; i<nums.length;i++){
            if (nums[i] <= nums[i-1]){
                return i;
            }
        }

        return nums.length;
    }

    /**
     * 算法思想： 一个快指针，一个慢指针
     * 遇见不同的值就把快指针处的值赋给慢指针的下一个
     * @param nums
     * @return
     */
    static int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int index = 0;
        for (int i=0; i< nums.length;i++){
            if (nums[i] != nums[index]){
                nums[++index] = nums[i];
            }
        }

        return index+1;
    }

}
