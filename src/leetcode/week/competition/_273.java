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
