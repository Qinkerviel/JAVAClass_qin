import java.util.ArrayList;
import java.util.List;

public class Cluster {
    //属性：id为簇编号，center为质心，points为簇内的点
    private int id;
    private Point center;
    private List<Point> points;

    //Cluster:功能：构造一个簇，质心用传入点的副本
    //入口参数：int id，意义：簇编号；Point first，意义：初始质心
    //返回值：无
    public Cluster(int id, Point first) {
        this.id = id;
        //这里必须new一个新的点。直接写center = first只是复制引用，
        //外面那个点一改，簇的质心跟着变，第一版就是错在这里，结果怎么跑都不对
        this.center = new Point(first.getX(), first.getY(), first.getName());
        this.points = new ArrayList<>();
    }
    //addPoint:功能：把一个点加入本簇
    //入口参数：Point p，意义：要加入的点
    //返回值：void，意义：无返回
    public void addPoint(Point p) {
        points.add(p);
    }
    //clearPoints:功能：清空簇内的点，每轮分配前都要清一次
    //无入口参数
    //返回值：void，意义：无返回
    public void clearPoints() {
        points.clear();
    }
    //updateCenter:功能：把簇内点的坐标取平均，算出新质心换掉旧的，并把新质心返回
    //无入口参数
    //返回值：Point，意义：新的质心。移动了多远让KMeans拿metric去算，簇自己不管距离
    public Point updateCenter() {
        if (points.isEmpty()) {
            return center;
        }
        double sumX = 0.0;
        double sumY = 0.0;
        for (int i = 0; i < points.size(); i++) {
            sumX += points.get(i).getX();
            sumY += points.get(i).getY();
        }
        //这里要new一个新的点，不能直接改center的坐标。KMeans那边是这么算移动量的：
        //old = c.getCenter(); fresh = c.updateCenter(); 再量old和fresh的距离
        //如果原地改，old和fresh就是同一个对象，距离恒等于0，第一轮就判收敛了
        center = new Point(sumX / points.size(), sumY / points.size(), center.getName());
        return center;
    }
    //avgDist:功能：算簇内每个点到质心的平均距离，越小说明簇越紧
    //入口参数：DistanceMetric m，意义：用哪种距离来量，由外面传进来
    //返回值：double，意义：平均离差
    public double avgDist(DistanceMetric m) {
        if (points.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < points.size(); i++) {
            sum += m.calculate(points.get(i), center);
        }
        return sum / points.size();
    }
    //getId、getCenter、getPoints:功能：给外面读属性，KMeans和写文件的类要用
    //无入口参数
    //返回值：int、Point、List<Point>，意义：簇编号、质心、簇内点
    public int getId() {
        return id;
    }
    public Point getCenter() {
        return center;
    }
    public List<Point> getPoints() {
        return points;
    }
}
