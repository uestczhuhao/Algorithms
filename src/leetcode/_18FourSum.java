package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18FourSum {


    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1,3,4);
//        boolean is= list.getClass().isInstance(ArrayList.class);
//        System.out.println(list.getClass().toString());
//        System.out.println(is);
//        int[] nums = new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        int[] nums = new int[]{1, 0, -1, 0, 2, -2};
        Arrays.sort(nums);
        System.out.println(fourSum(nums, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        return kSum(nums, 4, target, 0);
    }

    /**
     * A k sum solution
     *
     * @param nums   input num array
     * @param k      k sum
     * @param target target number
     * @param index  search in [index, nums.length-1]
     * @return
     */
    public static List<List<Integer>> kSum(int[] nums, int k, int target, int index) {
        List<List<Integer>> res = new ArrayList<>();

        if (k > 2) {
            for (int i = index; i < nums.length - k + 1; i++) {
                List<List<Integer>> lessRes = new ArrayList<>();
                lessRes = kSum(nums, k - 1, target - nums[i], i + 1);
                if (!lessRes.isEmpty()) {
                    for (List<Integer> l : lessRes) {
                        l.add(0, nums[i]);
                    }
                    res.addAll(lessRes);
                }

                while (i< nums.length -1 && nums[i] == nums[i+1]){
                    i++;
                }
            }
        } else {
            res = _2Sum(nums, target, index);
        }

        return res;
    }

    static List<List<Integer>> _2Sum(int[] nums, int target, int index) {
        List<List<Integer>> res = new ArrayList<>();
        int high = nums.length - 1;
        int low = index;

        while (low < high) {
            if (nums[low] + nums[high] == target) {
                ArrayList<Integer> twoRes = new ArrayList<>();
                twoRes.add(nums[low]);
                twoRes.add(nums[high]);
                res.add(twoRes);
                while (low < high && nums[low] == nums[low + 1]) low++;
                while (low < high && nums[high] == nums[high - 1]) high--;
                index++;
                high--;
            } else if (nums[low] + nums[high] < target) {
                low++;
            } else {
                high--;
            }

        }
        return res;
    }


}
