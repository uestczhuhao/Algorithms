package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author mizhu
 * @date 20-1-24 上午11:24
 * <p>
 * * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，
 * 在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class _42TrappingRainWater {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {5, 5, 1, 7, 1, 1, 5, 2, 7, 6};
        _42TrappingRainWater t = new _42TrappingRainWater();
        System.out.println(t.trap(height));
        System.out.println(trap1(height));
    }

    /**
     * 对某个位置而言，取左边和右边的最大值leftHeight和rightHeight
     * 取min(leftHeight, rightHeight) - h(i)，累加即可
     * <p>
     * 双指针法，保存left，right和左侧最大值（目前为止）leftHeight和右侧最大值（目前为止）
     * 若leftHeight <= rightHeight，则对当前left，可以计算当前位置盛水量
     * 因为当前的leftHeight是真实值，rightHeight不一定，但是min(leftHeight, rightHeight) = leftHeight可以确定
     * 反之，可以计算right位置当前盛水量
     */
    public static int trap1(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }
        int left = 0, right = height.length - 1;
        int leftHeight = height[left], rightHeight = height[right];
        int totalWater = 0;
        while (left <= right) {
            if (leftHeight <= rightHeight) {
                totalWater += (Math.max(leftHeight - height[left], 0));
                if (height[left] > leftHeight) {
                    leftHeight = height[left];
                }
                left++;
            } else {
                totalWater += (Math.max(rightHeight - height[right], 0));
                if (height[right] > rightHeight) {
                    rightHeight = height[right];
                }
                right--;
            }
        }
        return totalWater;
    }

    /**
     * 栈中存放下标，高度递减，一旦遇到高度大于栈顶位置的位置（假设为c），则可能出现低洼
     * 此时弹出栈顶a，此时a和栈顶的b，当前位置c形成低洼，计算 min(b, c) * dis，累加即可，其中dis = (c - b) + 1
     * 计算完后b成为新的栈顶元素，继续上诉过程
     */
    public int trap(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();
        int index = 0;
        int result = 0;
        while (index < height.length) {
            while (!stack.isEmpty() && height[index] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = index - stack.peek() - 1;
                int curHeight = Math.min(height[index], height[stack.peek()]) - height[top];
                result += distance * curHeight;
            }
            stack.push(index++);
        }

        return result;
    }
}
