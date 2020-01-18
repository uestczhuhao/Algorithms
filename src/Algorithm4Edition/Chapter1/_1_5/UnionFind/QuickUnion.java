package Algorithm4Edition.Chapter1._1_5.UnionFind;

import java.util.stream.IntStream;

/**
 * Created by mizhu on 20-1-5 下午3:45
 */
public class QuickUnion extends AbstractUnionFind {

    public QuickUnion(int n) {
        this.N = n;
        // 初始化时没有连接，触点数即为连通分量个数
        count = n;
        id = IntStream.rangeClosed(0, N - 1).toArray();
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 如果p和q不在同一个连通分量，则
        id[pRoot] = qRoot;
        count --;
    }

    @Override
    public int find(int p) {
        // 找到p所在链的根节点，其特征为 p == id[p]
        while (p != id[p]) {
            p = id[p];
        }

        return p;
    }

}
