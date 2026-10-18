import java.util.ArrayList;
import java.util.List;

public class KMeans {
    //属性：k为簇数，maxIter为最多迭代多少轮，eps为收敛阈值（质心移动小于它就算收敛）
    //metric是距离度量的接口引用，具体传进来是欧氏还是曼哈顿，这里不知道，也不该知道
    private int k;
    private int maxIter;
    private double eps;
    private DistanceMetric metric;
    private List<Cluster> clusters;
    private int iterations = 0;

    //KMeans:功能：构造聚类器，把参数和距离度量都存起来
    //入口参数：int k，意义：簇的个数；int maxIter，意义：最大迭代次数；
    //         double eps，意义：收敛阈值；DistanceMetric metric，意义：距离度量，从外面传
    //返回值：无
    public KMeans(int k, int maxIter, double eps, DistanceMetric metric) {
        //构造的时候就检查掉，别等算距离的时候再空指针，那样堆栈还指在KMeans里面，不好查
        if (metric == null) {
            throw new IllegalArgumentException("距离度量不能为null");
        }
        //k要是传个0进来，clusters是空的，findNearest里clusters.get(0)就直接越界了
        if (k < 1 || maxIter < 1) {
            throw new IllegalArgumentException("k和最大迭代次数都得是正数");
        }
        this.k = k;
        this.maxIter = maxIter;
        this.eps = eps;
        this.metric = metric;
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
            //给每个点找最近的质心，距离统一走metric.calculate，这里不出现任何具体公式
            for (int i = 0; i < data.size(); i++) {
                Point p = data.get(i);
                Cluster nearest = findNearest(p);
                nearest.addPoint(p);
            }
            //更新质心。移动了多少要拿新旧质心量一下，量的方式也跟着metric走，
            //所以换成曼哈顿距离的时候，收敛的判断标准其实也跟着变了
            double maxMoved = 0.0;
            for (int i = 0; i < clusters.size(); i++) {
                Cluster c = clusters.get(i);
                Point oldCenter = c.getCenter();
                Point newCenter = c.updateCenter();
                double moved = metric.calculate(oldCenter, newCenter);
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
    //findNearest:功能：找一个离p最近的质心所在的簇
    //入口参数：Point p，意义：要分配的点
    //返回值：Cluster，意义：离它最近的那个簇
    private Cluster findNearest(Point p) {
        Cluster nearest = null;
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < clusters.size(); i++) {
            Cluster c = clusters.get(i);
            double dist = metric.calculate(p, c.getCenter());
            if (dist < minDist) {
                minDist = dist;
                nearest = c;
            }
        }
        return nearest;
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
    //getMetric:功能：返回当前用的距离度量，写结果文件时要用它的类名
    //无入口参数
    //返回值：DistanceMetric，意义：距离度量对象
    public DistanceMetric getMetric() {
        return metric;
    }
}
