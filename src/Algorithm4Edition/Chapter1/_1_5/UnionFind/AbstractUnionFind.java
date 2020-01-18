package Algorithm4Edition.Chapter1._1_5.UnionFind;

/**
 * Created by mizhu on 19-12-29 下午11:05
 * 采用数组id保存每个触点存在的连通分量
 */
public abstract class AbstractUnionFind implements UnionFind {
    /**
     * 触点的个数
     */
    int N;
    /**
     * 触点索引
     */
    int[] id;

    /**
     * 连通分量的数量
     */
    int count;

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
