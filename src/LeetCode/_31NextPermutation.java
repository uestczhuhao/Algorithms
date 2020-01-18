package LeetCode;

import java.util.Arrays;

public class _31NextPermutation {
    public static void main(String[] args) {
        int[] nums = {5,4,3,2,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
    static void nextPermutation(int[] nums){
        if (nums == null || nums.length <= 1){
            return;
        }
        int index = nums.length-1;
        //找到需要交换的位置，例如123654，则index = 2，num[index] = 3
        while (index > 0){
            if (nums[index - 1] < nums[index]){
                break;
            }
            index -- ;
        }

        /*
        判断index是否为0
        1. 若是，则序列已经是最大，调整至最小即可
        2. 若否，则将 i-1 处值和[i,n-1]的最小值交换（保证nums[i-1] < nums[j]，j为交换位置），
        并调整[i,n-1]至最小序列（即升序），由于其已经为最大序列，只需首尾双指针交换算法即可
         */
        if (index == 0) {
            sortConvert(nums, 0, nums.length - 1);
        } else {
            int i=nums.length - 1;
            for (; i >= index; i--){
                if (nums[i] > nums[index - 1]){
                    break;
                }
            }
            swap(nums, i, index-1);
            sortConvert(nums, index, nums.length - 1);
        }
    }

    private static void swap(int[] nums, int source,int target){
        int tmp = nums[source];
        nums[source] = nums[target];
        nums[target] = tmp;
    }
    static void sortConvert(int[] nums, int start, int end){
        while (start < end){
            swap(nums, start, end);
            start ++ ;
            end -- ;
        }
    }
}
