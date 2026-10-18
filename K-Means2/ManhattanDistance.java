//曼哈顿距离，只能横着竖着走的时候走的距离，|dx|+|dy|，像在城市街区里绕
public class ManhattanDistance implements DistanceMetric {

    //calculate:功能：算曼哈顿距离
    //入口参数：Point p1，意义：第一个点；Point p2，意义：第二个点
    //返回值：double，意义：两点间的曼哈顿距离
    @Override
    public double calculate(Point p1, Point p2) {
        double dx = Math.abs(p1.getX() - p2.getX());
        double dy = Math.abs(p1.getY() - p2.getY());
        return dx + dy;
    }
}
