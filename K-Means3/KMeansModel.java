import java.util.ArrayList;
import java.util.List;

//K-Means：先挑k个质心，然后"分配—更新"来回倒，直到质心基本不动
public class KMeansModel extends BaseClusterModel {

    //KMeansModel:功能：把簇数和迭代上限交给父类
    //入口参数：int k，意义：簇数；int maxIterations，意义：最多迭代几轮
    //返回值：无
    public KMeansModel(int k, int maxIterations) {
        super(k, maxIterations);
    }

    //computeDistance:功能：K-Means这边用欧氏距离，直线距离
    //入口参数：Point a，意义：第一个点；Point b，意义：第二个点
    //返回值：double，意义：两点的欧氏距离
    @Override
    protected double computeDistance(Point a, Point b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    //fit:功能：跑K-Means，跑完clusters和centroids就有值了
    //无入口参数
    //返回值：void，意义：无返回
    @Override
    public void fit() {
        //初始质心直接拿前k个点，不用随机数，这样每次跑结果都一样，好核对
        centroids = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            centroids.add(dataPoints.get(i));
        }
        for (int round = 1; round <= maxIterations; round++) {
            //每轮都要重新建空簇，上一次分的必须扔掉，不然点是往上加的
            clusters = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                clusters.add(new ArrayList<Point>());
            }
            //分配：每个点去找离它最近的质心
            for (Point p : dataPoints) {
                int nearest = 0;
                double min = computeDistance(p, centroids.get(0));
                for (int i = 1; i < k; i++) {
                    double d = computeDistance(p, centroids.get(i));
                    if (d < min) {
                        min = d;
                        nearest = i;
                    }
                }
                clusters.get(nearest).add(p);
            }
            //更新：质心挪到簇的平均位置，顺便记下挪了多远
            double shift = 0;
            for (int i = 0; i < k; i++) {
                if (clusters.get(i).isEmpty()) {
                    continue;   //空簇就留着老质心，别把它弄丢了
                }
                Point newCenter = mean(clusters.get(i));
                shift += computeDistance(newCenter, centroids.get(i));
                centroids.set(i, newCenter);
            }
            System.out.println("第" + round + "轮：质心总共挪了" + String.format("%.4f", shift));
            if (shift < 1e-6) {
                System.out.println("质心不动了，收敛，一共" + round + "轮");
                break;
            }
        }
    }
}
