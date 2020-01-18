package Algorithm4Edition.Chapter1._1_5.UnionFind;

/**
 * Created by mizhu on 19-12-29 下午11:01
 */
public interface UnionFind {
    /**
     * 在p和q之间添加一条连接
     */
    void  union(int p, int q);

    /**
     * p 所在的分量的标识符
     * @param p 范围 0~N-1
     */
    int find(int p);

    /**
     * 如果 p 和 q 存在同一个分量中，则返回true,否则返回false
     */
    boolean connected(int p, int q);

    /**
     * 连通分量的数量
     */
    int count();
}
