package leetcode;

import java.util.*;

/**
 * @author mizhu
 * @date 2020/4/27 18:01
 * 在无限长的数轴（即 x 轴）上，我们根据给定的顺序放置对应的正方形方块。
 * <p>
 * 第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，其中 left 表示该方块最左边的点位置(positions[i][0])，side_length 表示该方块的边长(positions[i][1])。
 * <p>
 * 每个方块的底部边缘平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落而下。在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
 * <p>
 * 方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
 * <p>
 *  
 * <p>
 * 返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ..., positions[i] 表示的方块掉落结束后，目前所有已经落稳的方块堆叠的最高高度。
 * <p>
 *  
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1, 2], [2, 3], [6, 1]]
 * 输出: [2, 5, 5]
 * 解释:
 * <p>
 * 第一个方块 positions[0] = [1, 2] 掉落：
 * _aa
 * _aa
 * -------
 * 方块最大高度为 2 。
 * <p>
 * 第二个方块 positions[1] = [2, 3] 掉落：
 * __aaa
 * __aaa
 * __aaa
 * _aa__
 * _aa__
 * --------------
 * 方块最大高度为5。
 * 大的方块保持在较小的方块的顶部，不论它的重心在哪里，因为方块的底部边缘有非常大的粘性。
 * <p>
 * 第三个方块 positions[1] = [6, 1] 掉落：
 * __aaa
 * __aaa
 * __aaa
 * _aa
 * _aa___a
 * --------------
 * 方块最大高度为5。
 * <p>
 * 因此，我们返回结果[2, 5, 5]。
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入: [[100, 100], [200, 100]]
 * 输出: [100, 100]
 * 解释: 相邻的方块不会过早地卡住，只有它们的底部边缘才能粘在表面上。
 *  
 * <p>
 * 注意:
 * <p>
 * 1 <= positions.length <= 1000.
 * 1 <= positions[i][0] <= 10^8.
 * 1 <= positions[i][1] <= 10^6.
 */
public class _699FallingSquares {
//    public static void main(String[] args) {
//        int[] a = {1, 2};
//        int[] b = {2, 3};
//        int[] c = {3, 7};
//        int[][] aa = new int[3][2];
//        aa[0] = a;
//        aa[1] = b;
//        aa[2] = c;
//        _699FallingSquares t = new _699FallingSquares();
////        System.out.println(t.fallingSquares(aa));
//        int[][] bb = {{50, 47}, {95, 48}, {88, 77}, {84, 3}, {53, 87}, {98, 79}, {88, 28}, {13, 22}, {53, 73}, {85, 55}};
//        System.out.println(t.fallingSquares(bb));
//    }

    /**
     * 不要思考“哪个方块影响次此位置的高度？”，应该思考“一个方块影响哪些位置的高度？”。
     */
    public List<Integer> fallingSquares(int[][] positions) {
        if (positions == null || positions.length == 0) {
            return new ArrayList<>();
        }

        int[] high = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int iLeft = positions[i][0];
            int iSize = positions[i][1];
            int iRight = iLeft + iSize;
            // 初始化每个位置箱子的高度
            high[i] += iSize;

            for (int j = i + 1; j < positions.length; j++) {
                int jLeft = positions[j][0];
                int jSize = positions[j][1];
                int jRight = jLeft + jSize;
                // 如果i，j相交，则更新j处的高度
                // 第i个正方形放下后，如果和第j个正方形交叉，则第j个正方形的位置高度也被更新
                if (jLeft < iRight && iLeft < jRight) {
                    // 有可能i=0时，已经把high[j]值更新为更大
                    // 当i=1时，h[1]比h[0]小，此时取更大的h[0]，而不是h[1]
                    high[j] = Math.max(high[j], high[i]);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        int currentHigh = 0;
        for (int h : high) {
            currentHigh = Math.max(h, currentHigh);
            ans.add(currentHigh);
        }

        return ans;

    }

    public static void main(String[] args) {
        _699FallingSquares t = new _699FallingSquares();
//        int[][] p = {{4, 1}, {6, 9}, {6, 8}, {1, 9}, {9, 8}};
        int[][] p = {{6, 4}, {2, 7}, {6, 9}};
//        int[][] p = {{4, 6}, {4, 2}, {4, 3}};
        System.out.println(t.fallingSquares1(p));
    }

    public List<Integer> fallingSquares1(int[][] positions) {

        Set<Integer> positionSet = new HashSet<>();
        for (int[] pos : positions) {
            positionSet.add(pos[0]);
            positionSet.add(pos[0] + pos[1] - 1);
        }

        int[] sortPosition = new int[positionSet.size()];
        int i = 0;
        for (int pos : positionSet) {
            sortPosition[i++] = pos;
        }
        Arrays.sort(sortPosition);
        Map<Integer, Integer> indexMap = new HashMap<>(sortPosition.length);
        for (i = 0; i < sortPosition.length; i++) {
            indexMap.put(sortPosition[i], i);
        }

        SegmentTree segmentTree = new SegmentTree(sortPosition.length);
        List<Integer> result = new ArrayList<>();
        int maxHeight = 0;
        for (int[] pos : positions) {
            int left = indexMap.get(pos[0]);
            int right = indexMap.get(pos[0] + pos[1] - 1);
            int height = segmentTree.query(left, right) + pos[1];
            segmentTree.update(left, right, height);
            maxHeight = Math.max(maxHeight, height);
            result.add(maxHeight);
        }

        return result;
    }

    private class SegmentTree {
        int offset;
        int[] lineTree;

        SegmentTree(int n) {
            lineTree = new int[2 * n];
            offset = n;
        }

        /**
         * 主要工作在update时
         *
         * @param left   左边界
         * @param right  右边界
         * @param height 要更新的高度
         */
        public void update(int left, int right, int height) {
            int leftPos = left + offset;
            int rightPos = right + offset;

            // 先更新left到right的原始值
            for (int i = leftPos; i <= rightPos; i++) {
                lineTree[i] = Math.max(lineTree[i], height);
                updatePosition(i);
            }
        }

        private void updatePosition(int position) {
            while (position >= 1) {
                int lChild = 2 * position;
                int rChild = 2 * position + 1;
                if (rChild < 2 * offset) {
                    lineTree[position] = Math.max(lineTree[lChild], lineTree[rChild]);
                }
                position >>= 1;
            }
        }

        public int query(int left, int right) {
            int leftPos = left + offset;
            int rightPos = right + offset;
            int target = 0;
            while (leftPos <= rightPos) {
                if ((leftPos & 1) == 1) {
                    target = Math.max(lineTree[leftPos++], target);
                }

                if ((rightPos & 1) == 0) {
                    target = Math.max(lineTree[rightPos--], target);
                }

                leftPos >>= 1;
                rightPos >>= 1;
            }

            return target;
        }
    }
}



