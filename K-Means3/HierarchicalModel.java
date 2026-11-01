import java.util.ArrayList;
import java.util.List;

//层次聚类（凝聚式）：一开始每个点自己算一簇，然后不停把最近的两簇合成一簇，
//合到只剩k簇为止。簇间距离用最短距离法：两簇里最近的那对点离多近，这两簇就多近。
public class HierarchicalModel extends BaseClusterModel {

    //HierarchicalModel:功能：层次聚类不迭代，maxIterations传0占位
    //入口参数：int k，意义：最后要剩几簇
    //返回值：无
    public HierarchicalModel(int k) {
        super(k, 0);
    }

    //computeDistance:功能：层次聚类这边用曼哈顿距离，因为要聚的数据是网格状的
    //入口参数：Point a，意义：第一个点；Point b，意义：第二个点
    //返回值：double，意义：两点的曼哈顿距离
    @Override
    protected double computeDistance(Point a, Point b) {
        double dx = Math.abs(a.getX() - b.getX());
        double dy = Math.abs(a.getY() - b.getY());
        return dx + dy;
    }

    //fit:功能：跑层次聚类，从N簇一路合到k簇
    //无入口参数
    //返回值：void，意义：无返回
    @Override
    public void fit() {
        //开局：每个点单独一簇
        clusters = new ArrayList<>();
        for (Point p : dataPoints) {
            List<Point> one = new ArrayList<>();
            one.add(p);
            clusters.add(one);
        }
        int step = 0;
        while (clusters.size() > k) {
            //扫一遍所有簇对，找出最像的那一对
            int first = 0;
            int second = 1;
            double min = Double.MAX_VALUE;
            for (int i = 0; i < clusters.size(); i++) {
                for (int j = i + 1; j < clusters.size(); j++) {
                    double d = clusterDistance(clusters.get(i), clusters.get(j));
                    if (d < min) {
                        min = d;
                        first = i;
                        second = j;
                    }
                }
            }
            //合并：把second整簇倒进first，然后删掉second
            clusters.get(first).addAll(clusters.get(second));
            clusters.remove(second);
            step++;
            System.out.println("第" + step + "次合并：两簇距离" + String.format("%.4f", min) + "，还剩" + clusters.size() + "簇");
        }
        //质心最后一起算，每簇取平均
        centroids = new ArrayList<>();
        for (List<Point> c : clusters) {
            centroids.add(mean(c));
        }
    }

    //clusterDistance:功能：算两簇之间的距离，最短距离法，就是两簇里最近那对点的距离
    //入口参数：List<Point> c1，意义：第一簇；List<Point> c2，意义：第二簇
    //返回值：double，意义：两簇之间的距离
    private double clusterDistance(List<Point> c1, List<Point> c2) {
        double min = Double.MAX_VALUE;
        for (Point p : c1) {
            for (Point q : c2) {
                double d = computeDistance(p, q);
                if (d < min) {
                    min = d;
                }
            }
        }
        return min;
    }
}
