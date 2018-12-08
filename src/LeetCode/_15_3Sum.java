package LeetCode;

import java.util.*;

public class _15_3Sum {
    /**
     * The idea is to sort an input array and then run through all indices of a possible first element of a triplet.
     * For each possible first element we make a standard bi-directional 2Sum sweep of the remaining part of the array.
     * Also we want to skip equal elements to avoid duplicates in the answer without making a set or smth like that.
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3){
            return res;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            //jump duplicate num
            if (i ==0 || (i>0 && nums[i] != nums[i-1])){
                int low = i+1, high = nums.length-1,sum = 0-nums[i];
                while (low < high){
                    if (nums[low] + nums[high] == sum){
                        res.add(Arrays.asList(nums[i],nums[low],nums[high]));
                        while (low < high && nums[low]== nums[low+1]) low++;
                        while (low < high && nums[high] == nums[high-1]) high--;
                        low ++; high--;
                    } else if (nums[low] + nums[high] < sum) {
                        low ++;
                    } else {
                        high--;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _15_3Sum t = new _15_3Sum();
//        System.out.println(t.threeSum(new int[]{-1,0,2,1,-4,-1}));
        System.out.println(t.threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));  //[[-4,-2,6],[-4,0,4],[-4,1,3],[-4,2,2],[-2,-2,4],[-2,0,2]]
    }
}
