package LeetCode;

import javax.swing.plaf.PanelUI;
import java.util.LinkedList;

/**
 * @author mizhu
 * @date 20-1-24 下午10:03
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class _84LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] height = {2, 1, 5, 6, 2, 3};
//        int[] height = {2, 4, 6, 8, 100};
        System.out.println(largestRectangleArea(height));
//        System.out.println(test1(height));
    }

    public static int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int oldTop = stack.pop();
                int leftNeighbor = stack.isEmpty() ? -1 : stack.peek();
                int width = i - leftNeighbor - 1;
                maxArea = Math.max(maxArea, heights[oldTop] * width);
            }
            stack.push(i);

        }
        while (!stack.isEmpty()) {
            int oldTop = stack.pop();
            int leftNeighbor = stack.isEmpty() ? -1 : stack.peek();
            int width = heights.length - leftNeighbor - 1;
            maxArea = Math.max(maxArea, heights[oldTop] * width);
        }
        return maxArea;
    }

}
