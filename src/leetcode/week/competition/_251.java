package leetcode.week.competition;

/**
 * @author mizhu
 * @date 2021/7/25 10:30
 */
public class _251 {
    public static void main(String[] args) {
        _251 t = new _251();
        String s = "leetcode";
//        System.out.println(t.getLucky(s, 2));
        int[] change = {0, 9, 2, 3, 3, 2, 5, 5, 5, 5};
        String num = "334111";
//        System.out.println(t.maximumNumber(num, change));
//        int[][] students = {{0, 0}, {0, 0}, {0, 0}};
        int[][] students = {{1, 1, 0}, {1, 0, 1}, {0, 0, 1}};
//        int[][] mentors = {{1, 1}, {1, 1}, {1, 1}};
        int[][] mentors = {{1, 0, 0}, {0, 0, 1}, {1, 1, 0}};
        System.out.println(t.maxCompatibilitySum(students, mentors));
    }

    int maxComp = 0;
    int row, col;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        row = students.length;
        col = students[0].length;
        int[][] compSum = new int[row][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                int curCom = 0;
                for (int k = 0; k < col; k++) {
                    if (students[i][k] == mentors[j][k]) {
                        curCom++;
                    }
                }
                compSum[i][j] = curCom;
            }
        }

        computeComp(compSum, 0, 0, new boolean[row]);
        return maxComp;
    }

    private void computeComp(int[][] sum, int curRow, int curCom, boolean[] visit) {
        if (curRow == row) {
            maxComp = Math.max(curCom, maxComp);
            return;
        }

        for (int j = 0; j < row; j++) {
            if (visit[j]) {
                continue;
            }
            visit[j] = true;
            computeComp(sum, curRow + 1, curCom + sum[curRow][j], visit);
            visit[j] = false;
        }
    }


    public String maximumNumber(String num, int[] change) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < num.length() && num.charAt(i) - '0' >= change[num.charAt(i) - '0']; i++) {
            sb.append(num.charAt(i));
        }

        for (; i < num.length() && num.charAt(i) - '0' <= change[num.charAt(i) - '0']; i++) {
            sb.append(change[num.charAt(i) - '0']);
        }

        while (i < num.length()) {
            sb.append(num.charAt(i++));
        }
        return sb.toString();
    }

    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) - 'a' + 1);
        }
        String lucky = sb.toString();
        int res = 0;
        for (int j = 0; j < lucky.length(); j++) {
            int value = lucky.charAt(j) - '0';
            res += value;
        }
        for (int i = 2; i <= k; i++) {
            int tmp = res;
            res = 0;
            while (tmp > 0) {
                int val = tmp % 10;
                res += val;
                tmp /= 10;
            }
        }

        return res;
    }
}
