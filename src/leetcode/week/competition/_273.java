package leetcode.week.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _273 {
    public static void main(String[] args) {
        _273 t = new _273();
//        System.out.println(t.isSameAfterReversals(1800));
//        System.out.println(t.isSameAfterReversals(526));
//        System.out.println(t.isSameAfterReversals(0));
//        System.out.println(Arrays.toString(t.executeInstructions(2, new int[] {1, 1}, "LURD")));
//        System.out.println(Arrays.toString(t.executeInstructions(1, new int[] {0, 0}, "LRUD")));
    }

    public long[] getDistances(int[] arr) {
        Map<Integer, List<Integer>> valueIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            valueIndexMap.putIfAbsent(value, new ArrayList<>());
            valueIndexMap.get(value).add(i);
        }

        long[] ans = new long[arr.length];
        for (Map.Entry<Integer, List<Integer>> entry : valueIndexMap.entrySet()) {
            List<Integer> indexList = entry.getValue();
            if (indexList.size() == 1) {
                ans[indexList.get(0)] = 0;
                continue;
            }
            Collections.sort(indexList);
            Integer[] indexArr = indexList.toArray(new Integer[0]);
            long[] sumList = getSumAbsoluteDifferences(indexArr);
            for (int i = 0; i < indexArr.length; i++) {
                ans[indexArr[i]] = sumList[i];
            }
        }

        return ans;
    }

    public long[] getSumAbsoluteDifferences(Integer[] nums) {
        int len = nums.length;
        long[] left = new long[len];
        long right = 0;
        long[] ans = new long[len];
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] + (long) i * (nums[i] - nums[i - 1]);
        }

        for (int i = len - 2; i >= 0; i--) {
            right += (long) (len - 1 - i) * (nums[i + 1] - nums[i]);
            ans[i] = left[i] + right;
        }
        ans[len - 1] = left[len - 1];
        return ans;
    }


    public int[] executeInstructions(int n, int[] startPos, String s) {
        int[] ans = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int[] curPos = startPos.clone();
            boolean overstep = false;
            for (int j = i; j < s.length(); j++) {
                if (crossBorder(n, curPos, s.charAt(j))) {
                    ans[i] = j - i;
                    overstep = true;
                    break;
                }
            }
            if (!overstep) {
                ans[i] = s.length() - i;
            }
        }
        return ans;
    }

    private boolean crossBorder(int matrixSize, int[] curPos, char nextDir) {
        int[] nextDirection = direction(nextDir);
        curPos[0] += nextDirection[0];
        curPos[1] += nextDirection[1];
        return curPos[0] < 0 || curPos[1] < 0 || curPos[0] >= matrixSize || curPos[1] >= matrixSize;
    }

    private int[] direction(char dir) {
        if (dir == 'R') {
            return new int[] {0, 1};
        } else if (dir == 'L') {
            return new int[] {0, -1};
        } else if (dir == 'U') {
            return new int[] {-1, 0};
        } else {
            return new int[] {1, 0};
        }
    }

    public boolean isSameAfterReversals(int num) {
        if (num < 10) {
            return true;
        }

        return num % 10 != 0;
    }
}
