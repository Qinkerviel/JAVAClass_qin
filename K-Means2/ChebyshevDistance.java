//切比雪夫距离，两个坐标差里面取大的那个，max(|dx|,|dy|)，
//棋盘上国王走一步能到的格子都是距离1，就是这个意思
public class ChebyshevDistance implements DistanceMetric {

    //calculate:功能：算切比雪夫距离
    //入口参数：Point p1，意义：第一个点；Point p2，意义：第二个点
    //返回值：double，意义：两点间的切比雪夫距离
    @Override
    public double calculate(Point p1, Point p2) {
        double dx = Math.abs(p1.getX() - p2.getX());
        double dy = Math.abs(p1.getY() - p2.getY());
        return Math.max(dx, dy);
    }
}
