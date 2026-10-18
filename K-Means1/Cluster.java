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
        this.center = new Point(first.getX(), first.getY(), "C" + id);
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
    //updateCenter:功能：根据簇内所有点重新算质心，并返回质心移动的距离
    //无入口参数
    //返回值：double，意义：质心移动的距离，很小说明已经稳定
    public double updateCenter() {
        if (points.isEmpty()) {
            return 0.0;
        }
        double sx = 0.0;
        double sy = 0.0;
        for (int i = 0; i < points.size(); i++) {
            sx += points.get(i).getX();
            sy += points.get(i).getY();
        }
        int n = points.size();
        Point nc = new Point(sx / n, sy / n, "C" + id);
        double moved = center.distanceTo(nc);
        center = nc;
        return moved;
    }
    //avgDist:功能：算簇内每个点到质心的平均距离，越小说明簇越紧
    //无入口参数，意义：无
    //返回值：double，意义：平均离差
    public double avgDist() {
        if (points.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < points.size(); i++) {
            sum += center.distanceTo(points.get(i));
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
