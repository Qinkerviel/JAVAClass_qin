import java.util.ArrayList;
import java.util.List;

public class KMeans {
    //属性：k为簇数，maxIter为最多迭代多少轮，eps为收敛阈值（质心移动小于它就算收敛）
    private int k;
    private int maxIter;
    private double eps;
    private List<Cluster> clusters;
    private int iterations = 0;

    //KMeans:功能：构造聚类器，把三个参数先存起来
    //入口参数：int k，意义：簇的个数；int maxIter，意义：最大迭代次数；double eps，意义：收敛阈值
    //返回值：无
    public KMeans(int k, int maxIter, double eps) {
        this.k = k;
        this.maxIter = maxIter;
        this.eps = eps;
        this.clusters = new ArrayList<>();
    }
    //fit:功能：对数据点做K-Means聚类，返回最终的簇
    //入口参数：List<Point> data，意义：所有数据点
    //返回值：List<Cluster>，意义：分好的簇
    public List<Cluster> fit(List<Point> data) {
        if (data.size() < k) {
            throw new IllegalArgumentException("点的个数比K还少，没法分");
        }
        clusters.clear();
        iterations = 0;
        //初始质心取前k个点。随机挑的话每次结果都不一样，不好对比，所以按课本取前k个
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster(i, data.get(i)));
        }
        while (true) {
            iterations++;
            for (int i = 0; i < clusters.size(); i++) {
                clusters.get(i).clearPoints();
            }
            //给每个点找最近的质心
            for (int i = 0; i < data.size(); i++) {
                Point p = data.get(i);
                Cluster best = clusters.get(0);
                double min = p.distanceTo(best.getCenter());
                    for (int j = 1; j < clusters.size(); j++) {
                        double d = p.distanceTo(clusters.get(j).getCenter());
                        if (d < min) {
                            min = d;
                            best = clusters.get(j);
                        }
                    }
                best.addPoint(p);
            }
            //更新质心，记录这一轮最大的移动距离
            double maxMoved = 0.0;
            for (int i = 0; i < clusters.size(); i++) {
                double moved = clusters.get(i).updateCenter();
                if (moved > maxMoved) {
                    maxMoved = moved;
                }
            }
            if (maxMoved < eps) {
                break;
            }
            if (iterations >= maxIter) {
                break;
            }
        }
        return clusters;
    }
    //getIterations:功能：返回实际迭代的轮数，打印结果时要用
    //无入口参数
    //返回值：int，意义：迭代轮数
    public int getIterations() {
        return iterations;
    }
    //getClusters:功能：返回分好的簇
    //无入口参数
    //返回值：List<Cluster>，意义：簇的集合
    public List<Cluster> getClusters() {
        return clusters;
    }
}
