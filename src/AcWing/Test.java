package AcWing;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//@NotEmpty
public class Test {
    public static void main(String[] args) {
        Test t = new Test();
//        // abbdcfdhe
//        System.out.println(t.replaceDigits("a1b2c3d4e"));
//        //abcdef
//        System.out.println(t.replaceDigits("a1c1e1"));

//        SeatManager seatManager = new SeatManager(5); // 初始化 SeatManager ，有 5 个座位。
//        seatManager.reserve();    // 所有座位都可以预约，所以返回最小编号的座位，也就是 1 。
//        seatManager.reserve();    // 可以预约的座位为 [2,3,4,5] ，返回最小编号的座位，也就是 2 。
//        seatManager.unreserve(2); // 将座位 2 变为可以预约，现在可预约的座位为 [2,3,4,5] 。
//        seatManager.reserve();    // 可以预约的座位为 [2,3,4,5] ，返回最小编号的座位，也就是 2 。
//        seatManager.reserve();    // 可以预约的座位为 [3,4,5] ，返回最小编号的座位，也就是 3 。
//        seatManager.reserve();    // 可以预约的座位为 [4,5] ，返回最小编号的座位，也就是 4 。
//        seatManager.reserve();    // 唯一可以预约的是座位 5 ，所以返回 5 。
//        seatManager.unreserve(5); // 将座位 5 变为可以预约，现在可预约的座位为 [5] 。
//        int[] a = {10, 10, 10, 10, 10, 10, 10, 10000, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
//        System.out.println(t.maximumElementAfterDecrementingAndRearranging(a));

//        int[][] rooms = {{2, 2}, {1, 2}, {3, 2}};
        int[][] rooms = {{1, 4}, {2, 3}, {3, 5}, {4, 1}, {5, 2}};
        int[][] queries = {{3, 1}, {3, 3}, {5, 2}};
        System.out.println(Arrays.toString(t.closestRoom(rooms, queries)));
    }


    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int num = rooms.length;
        int[] next = new int[num];
        Arrays.fill(next, -20000001);
        Arrays.sort(rooms, Comparator.comparingInt(a -> a[0]));
        Map<Integer, Integer> roomIndexMap = new HashMap<>();
        for (int i = 0; i < rooms.length; i++) {
            roomIndexMap.put(rooms[i][0], i);
        }

        for (int i = 0; i < num - 1; i++) {
            int pre = i;
            for (int j = i + 1; j < num; j++) {
                if (pre < 0) {
                    break;
                }
                // 找到第一个比j小且id比j小的next了
                if (rooms[j][1] < rooms[pre][1]) {
                    next[j] = pre;
                    break;
                }
                pre--;
            }
        }

        for (int i = num - 1; i > 0; i--) {
            int ne = i;
            for (int j = i - 1; j >= 0; j--) {
                if (ne >= num) {
                    break;
                }
                int diff = rooms[j][0] - next[j];
                if (rooms[j][1] < rooms[ne][1] && rooms[ne][0] - rooms[j][0] < diff) {
                    next[j] = ne;
                    break;
                }
                ne++;
            }
        }

        int qNum = queries.length;
        int[] answer = new int[qNum];
        int index, qSize, qRoom;
//        for (int i = 0; i < qNum; i++) {
//            qRoom = queries[i][0];
//
//            if (qRoom > rooms[rooms.length - 1][0]) {
//                index = rooms[rooms.length - 1][0];
//            } else if (qRoom < rooms[0][0]) {
//                index =
//            }
//            index = roomIndexMap.get();
//            qSize = queries[i][1];
//            if (rooms[index][1] >= qSize) {
//                answer[i] = index;
//            } else {
//                answer[i] = find(rooms, next, index, qSize);
//            }
//        }

        return answer;
    }

    private int find(int[][] rooms, int[] next, int start, int size) {
        while (rooms[start][1] < size) {
            if (next[start] == -20000001) {
                return -1;
            }
            start = next[start];
        }

        return rooms[next[start]][0];
    }
}

