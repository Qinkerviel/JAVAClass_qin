//欧氏距离，就是平时说的两点间直线距离，√(dx²+dy²)
public class EuclideanDistance implements DistanceMetric {

    //calculate:功能：算欧氏距离
    //入口参数：Point p1，意义：第一个点；Point p2，意义：第二个点
    //返回值：double，意义：两点间的欧氏距离
    @Override
    public double calculate(Point p1, Point p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
