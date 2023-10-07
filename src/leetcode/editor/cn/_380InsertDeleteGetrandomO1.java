package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class _380InsertDeleteGetrandomO1 {
    public static void main(String[] args) {
        RandomizedSet t = new _380InsertDeleteGetrandomO1().new RandomizedSet();
        System.out.println(t.remove(0));
        System.out.println(t.remove(0));
        System.out.println(t.insert(0));
        System.out.println(t.getRandom());
        System.out.println(t.remove(0));
        System.out.println(t.insert(0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class RandomizedSet {
        int[] data;
        int index;
        // 借助map实现插入和删除都是O(1)
        // 数组的插入和删除也都是O(1)
        Map<Integer, Integer> indexMap;
        Random random = new Random();

        public RandomizedSet() {
            data = new int[200_010];
            index = -1;
            indexMap = new HashMap<>();
        }

        public boolean insert(int val) {
            if (indexMap.containsKey(val)) {
                return false;
            }
            data[++index] = val;
            indexMap.put(val, index);
            return true;
        }

        public boolean remove(int val) {
            if (!indexMap.containsKey(val)) {
                return false;
            }
            int location = indexMap.get(val);
            int lastValue = data[index];
            data[location] = lastValue;
            indexMap.put(lastValue, location);
            indexMap.remove(val);
            index--;
            return true;
        }

        public int getRandom() {
            return data[random.nextInt(index + 1)];
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
