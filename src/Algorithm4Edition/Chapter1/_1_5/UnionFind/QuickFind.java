package Algorithm4Edition.Chapter1._1_5.UnionFind;

import java.util.stream.IntStream;

/**
 * Created by mizhu on 19-12-29 下午11:00
 */
public class QuickFind extends AbstractUnionFind {

    public QuickFind(int n) {
        this.N = n;
        // 初始化时没有连接，触点数即为连通分量个数
        count = n;
        id = IntStream.rangeClosed(0, N - 1).toArray();
    }

    @Override
    public void union(int p, int q) {
        if (p < 0 || p > N - 1 || q < 0 || q > N - 1 ) {
            throw new IllegalArgumentException("输入参数" + p + "不合法");
        }
        // 注意：不可少，否则会导致id[p]本身被修改，后序的记录得不到修改，见1.5.8
        int pId = id[p];
        int qId = id[q];

        // p和q已经在一个连通分量，不需要处理
        if (pId == qId) {
            return;
        }

        // 将p分量重命名为q分量
        for (int i=0;i<id.length;i++) {
            if(id[i] == pId) {
                id[i] = qId;
            }
        }
    }

    @Override
    public int find(int p) {
        if (p < 0 || p > N - 1) {
            throw new IllegalArgumentException("输入参数" + p + "不合法");
        }
        return id[p];
    }
}
