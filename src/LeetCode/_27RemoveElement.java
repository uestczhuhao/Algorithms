package LeetCode;

public class _27RemoveElement {

    /**
     * 通过赋值所有不等于目标值的元素，实现前n个元素都满足要求
     * @return
     */
    public static int removeElement(int[] nums, int val){
        if (nums == null || nums.length == 0){
            return Integer.MAX_VALUE;
        }

        int index = 0;
        for (int i=0; i< nums.length; i++){
            if (nums[i] != val){
                nums[index] = nums[i];
                index ++;
            }
        }

        return index;
    }
}
