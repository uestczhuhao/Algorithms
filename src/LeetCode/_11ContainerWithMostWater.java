package LeetCode;

public class _11ContainerWithMostWater {

    /**
     * 思路： 两个指针，一头一尾，记录此时高度height和area，此时maxArea = area
     * 移动height较小的那个指针，因为否则area会越来越小，不会有增加的可能
     * 在移动的过程中记录area，和maxArea对比，直到指针碰撞
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int low = 0, large = height.length - 1;

        int maxArea = 0;

        while (large > low) {
            if (height[low] >= height[large]) {
                maxArea = Math.max(maxArea, height[large] * (large - low));
                large--;
            } else {
                maxArea = Math.max(height[low] * (large - low), maxArea);
                low++;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        _11ContainerWithMostWater t = new _11ContainerWithMostWater();
//        System.out.println(t.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(t.maxArea(new int[]{1,3,4}));
    }
}
