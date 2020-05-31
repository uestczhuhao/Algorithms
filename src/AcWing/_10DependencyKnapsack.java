//package AcWing;
//
//import java.util.*;
//
///**
// * @author mizhu
// * @date 2020/3/22 15:37
//    TODO：后续搞定了树形dp再回头看这道题
// */
//public class _10DependencyKnapsack {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int itemNum = 0, totalVolume = 0;
//        itemNum = scanner.nextInt();
//        totalVolume = scanner.nextInt();
//
//        int[] volumes = new int[itemNum];
//        int[] weights = new int[itemNum];
//        int[] dependency = new int[itemNum];
//        int i = 0;
//        while (scanner.hasNext()) {
//            volumes[i] = scanner.nextInt();
//            weights[i] = scanner.nextInt();
//            dependency[i] = scanner.nextInt();
//            i++;
//        }
//
//        volumeArr = volumes;
//        wrightArr = weights;
//        dependencyArr = dependency;
//
//        System.out.println(maxValue(itemNum, totalVolume));
//    }
//
//    static int[] volumeArr = new int[0];
//    static int[] wrightArr = new int[0];
//    static int[] dependencyArr = new int[0];
//    static Map<Integer, Set<Integer>> downStreamMap = new HashMap<>();
//
//    public static int maxValue(int itemNum, int totalVolume) {
//        if (itemNum <= 0 || totalVolume <= 0) {
//            return 0;
//        }
//        Map<Integer, Set<Integer>> downSteams = new HashMap<>(dependencyArr.length);
//        // 根节点
//        int rootIndex = -1;
//        // 1. 构建下游map
//        for (int i = 0; i < dependencyArr.length; i++) {
//            if (dependencyArr[i] == -1) {
//                rootIndex = i;
//            } else {
//                dependencyArr[i] -= 1;
//            }
//            // 构造下游map，初始为空set
//            Set<Integer> downs = downSteams.getOrDefault(dependencyArr[i], new HashSet<>());
//            downs.add(i);
//            downSteams.put(dependencyArr[i], downs);
//        }
//        downStreamMap = downSteams;
//        Map<Integer, Integer> nodeMaxValueMap = new HashMap<>(dependencyArr.length);
//        // 2. 对每个节点，持续对其下游做0/1背包，直至根结点，收集每个节点的最大价值
//        return maxValueWithChild(totalVolume, rootIndex)[1];
//    }
//
//    /**
//     * int h[N],e[N],ne[N],idx;
//     * h数组是邻接表的头，它的下标是当前节点的标号，
//     * 值是当前结点第一条边的编号（其实是最后加入的那一条边），
//     * e数组是边的集合，它的下标是当前边的编号，数值是当前边的终点；
//     * ne是next edge，如果ne是-1表示当前结点没有下一条边，
//     * ne的下标是当前边的编号，数值是当前结点的下一条边的编号，idx用于保存每一条边的上一条边的编号。
//     * 这样我们就知道了当前结点的第一条边是几，
//     * 这个边的终点是那个结点，该节点的下一条边编号是几，那么邻接表就完成了
//     */
//    public static int maxValueWithChild(int totalVolume, int index) {
//        totalVolume = totalVolume - volumeArr[index];
//        if (totalVolume < 0) {
//            return 0;
//        }
//        int[] maxValue = new int[totalVolume + 1];
//        if (downStreamMap.get(index) == null) {
//            return wrightArr[index];
//        } else {
//            List<Integer> downIndexList = new ArrayList<>(downStreamMap.get(index));
//            Set<Integer> select = new HashSet<>();
//            for (int j = totalVolume; j >= 0; j--) {
//                for (Integer downIndex : downIndexList) {
//                    int maxWeight = maxValueWithChild(totalVolume, downIndex);
//                    int jthMax = maxValue[j - volumeArr[downIndex] + volumeArr[downIndex];
//                    if (maxValue[j] < jthMax) {
//                        select.add(downIndex);
//                        maxValue[j] = jthMax;
//                    }
//                }
//            }
//            for (Integer i : select) {
//                result[0] += volumeArr[i];
//            }
//            result[1] = maxValue[totalVolume];
//        }
//        return result;
//
//    }
//}

