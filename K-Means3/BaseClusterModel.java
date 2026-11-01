import java.util.ArrayList;
import java.util.List;

//聚类的抽象基类。K-Means和层次聚类都要加载数据、算距离、维护质心，这部分是共的，写在这里；
//不一样的是"用哪种距离"和"怎么迭代"，做成抽象方法丢给子类。这个类自己不能new。
//字段用protected：外面改不了，子类能直接用，不用写一堆getter/setter。
public abstract class BaseClusterModel {
    protected int k;                      //要分成几簇
    protected int maxIterations;          //最多迭代几轮，层次聚类不走迭代，用不上
    protected List<Point> dataPoints;     //所有点
    protected List<Point> centroids;      //每簇的质心
    protected List<List<Point>> clusters; //每簇里面装着哪些点

    //BaseClusterModel:功能：记下簇数和最大迭代次数，数据等loadData再给
    //入口参数：int k，意义：簇数；int maxIterations，意义：最大迭代次数
    //返回值：无
    public BaseClusterModel(int k, int maxIterations) {
        this.k = k;
        this.maxIterations = maxIterations;
        this.dataPoints = new ArrayList<>();
        this.centroids = new ArrayList<>();
        this.clusters = new ArrayList<>();
    }

    //loadData:功能：把点装进来，两个子类都要用，所以放父类
    //入口参数：List<Point> points，意义：要聚类的全部点
    //返回值：void，意义：无返回
    public void loadData(List<Point> points) {
        this.dataPoints = points;
    }

    //getCentroids、getClusters:功能：把结果交给外面看，字段是protected，外面拿不到只能走这里
    //无入口参数
    //返回值：List<Point>、List<List<Point>>，意义：质心列表、分好的簇
    public List<Point> getCentroids() {
        return centroids;
    }
    public List<List<Point>> getClusters() {
        return clusters;
    }

    //mean:功能：算一簇点的质心，就是把坐标各自取平均
    //入口参数：List<Point> group，意义：同一簇里的点
    //返回值：Point，意义：这簇的质心，名字留空
    protected Point mean(List<Point> group) {
        double sumX = 0;
        double sumY = 0;
        for (Point p : group) {
            sumX += p.getX();
            sumY += p.getY();
        }
        return new Point(sumX / group.size(), sumY / group.size());
    }

    //printResult:功能：把每个簇和它的质心打出来
    //入口参数：String title，意义：这次跑的是哪种聚类，打印时分得清
    //返回值：void，意义：无返回
    public void printResult(String title) {
        System.out.println("=== " + title + " ===");
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("簇" + i + " 共" + clusters.get(i).size() + "个点，质心" + centroids.get(i));
            for (Point p : clusters.get(i)) {
                System.out.print("  " + p);
            }
            System.out.println();
        }
    }

    //computeDistance:功能：算两个点的距离，用什么公式由子类定
    //父类这里只有一句声明，连是欧氏还是曼哈顿都不知道，这正是抽象方法的意义
    //入口参数：Point a，意义：第一个点；Point b，意义：第二个点
    //返回值：double，意义：两点距离
    protected abstract double computeDistance(Point a, Point b);

    //fit:功能：跑聚类。K-Means是一轮轮迭代，层次聚类是一层层合并，流程完全不同，只能各写各的
    //无入口参数
    //返回值：void，意义：无返回，结果放在clusters和centroids里
    public abstract void fit();
}
