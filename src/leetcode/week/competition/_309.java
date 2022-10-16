package leetcode.week.competition;

import java.util.HashMap;
import java.util.Map;

public class _309 {
    public static void main(String[] args) {
        String s = "abaccb";
        int[] dis = {1, 3, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        _309 t = new _309();
        System.out.println(t.checkDistances(s, dis));
    }

    /**
     * 双指针法：初始化preSum，存放[left, right]的累加值（由于[left, right]的元素都是优雅的，累积不会有进位）
     * 当preSum和nums[right]按位与==0时，right向右移动
     * 当preSum和nums[right]按位与>0时，把left向右移动（因为以right为右边界，left从0开始往右遍历，滑动窗口在此时收缩）
     * 注：由于优雅子数组的特性为每个二进制位只有一个1，因此把它们相加后，再与nums[right]做运算的结果，
     *    和nums[right]与每一个元素分别做运算的结果相同，因此这样可以O(1)判断当前元素是否与前面的元素按位与的结果为0
     */
    public int longestNiceSubarray(int[] nums) {
        int preSum = 0, left = 0;
        int ans = 0;
        for (int right = 0; right < nums.length; right++) {
            while ((preSum & nums[right]) > 0) {
                preSum -= nums[left++];
            }
            preSum += nums[right];
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }


    // 位置curPos为key，剩余步数k -> 解决方法数ans 为value
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    /**
     * dfs解法，dfs(curPos, endPos, k)表示从curPos，恰好k步移动到endPos的方法数
     * 其中dfs(curPos, endPos, k) = dfs(curPos - 1, endPos, k - 1) + dfs(curPos + 1, endPos, k - 1)，表示下一步向左/右移动
     * 优化：1. 剪枝：当剩余步数k < abs(curPos, endPos)时，不可能移动到endPos，直接返回
     * 2. 记忆化搜索，所在位置为curPos，剩余k步时的结果可以缓存下来，下次直接拿即可
     */
    public int numberOfWays(int startPos, int endPos, int k) {
        return dfs(startPos, endPos, k);
    }

    private int dfs(int curPos, int endPos, int k) {
        if (k == 0) {
            return curPos == endPos ? 1 : 0;
        }
        // 剪枝
        if (k < Math.abs(curPos - endPos)) {
            return 0;
        }
        // 记忆化搜索
        if (map.containsKey(curPos) && map.get(curPos).containsKey(k)) {
            return map.get(curPos).get(k);
        }

        int left = dfs(curPos - 1, endPos, k - 1);
        int right = dfs(curPos + 1, endPos, k - 1);
        int ans = (left + right) % 1000_000_007;
        Map<Integer, Integer> curMap = new HashMap<>();
        curMap.put(k, ans);
        map.put(curPos, curMap);
        return ans;
    }

    public boolean checkDistances(String s, int[] distance) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (map.containsKey(s.charAt(i))) {
                map.put(cur, i - map.get(cur) - 1);
            } else {
                map.put(cur, i);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char curCh = entry.getKey();
            if (distance[curCh - 'a'] != entry.getValue()) {
                return false;
            }
        }
        return true;
    }

}
