//加权欧氏距离：x方向和y方向各给一个权重，权重小的方向"不太敏感"。
//注意这个类是后面加的，DistanceMetric和前面三个实现类一行都没改——
//加功能只是加新文件，不动老代码，这就是接口带来的好处（开闭原则）。
public class WeightedEuclideanDistance implements DistanceMetric {
    //属性：wx、wy为两个方向的权重，都是正数，1和1时退化成普通欧氏距离
    private double wx;
    private double wy;

    //WeightedEuclideanDistance:功能：记下两个方向的权重
    //入口参数：double wx，意义：x方向权重；double wy，意义：y方向权重
    //返回值：无
    public WeightedEuclideanDistance(double wx, double wy) {
        this.wx = wx;
        this.wy = wy;
    }

    //calculate:功能：算加权欧氏距离，√(wx·dx²+wy·dy²)
    //入口参数：Point p1，意义：第一个点；Point p2，意义：第二个点
    //返回值：double，意义：两点间的加权欧氏距离
    @Override
    public double calculate(Point p1, Point p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(wx * dx * dx + wy * dy * dy);
    }
}
