package Algorithm4Edition.Chapter1._1_5.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by mizhu on 20-1-5 下午4:18
 */
public class WeghtQuickUnion extends AbstractUnionFind {
    /**
     * 此当前触点连通分量的规模，用于小的连通分量合并到大的连通分量
     */
    private int[] sz;

    public WeghtQuickUnion(int n) {
        this.N = n;
        // 初始化时没有连接，触点数即为连通分量个数
        count = n;
        id = IntStream.rangeClosed(0, N - 1).toArray();
        sz = new int[n];
        // 两种方法，任取一种
        Arrays.fill(sz, 1);
//        sz = IntStream.generate(() -> 1).limit(n).toArray();
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count --;
    }

    @Override
    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }
        // 加上路径压缩，即把路程上遇到的每个触点直接连接到root
        // 可以达到每次均摊成本接近O(1)
        while (p != id[p]) {
            int previous = id[p];
            id[p] = root;
            p = id[previous];
        }

        return root;
    }

    public static void main(String[] args) {
        int size =10;
        int[] t1 = new int[size];
        int[] t2;
        Arrays.fill(t1,1);
        t2 = IntStream.generate(() -> 1).limit(size).toArray();
        System.out.println(Arrays.toString(t1));
        System.out.println(Arrays.toString(t2));
    }
}
