public class Point {
    //属性：x、y为横纵坐标，name为点的名字（如P0），只用来打印
    //这一版Point只负责存数据，distanceTo已经删掉了，算距离的事交给DistanceMetric
    private double x;
    private double y;
    private String name;

    //Point:功能：构造一个点，名字先给空
    //入口参数：double x，意义：横坐标；double y，意义：纵坐标
    //返回值：无
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.name = "";
    }
    //Point:功能：构造一个点并指定名字，读文件时用这个
    //入口参数：double x，意义：横坐标；double y，意义：纵坐标；String name，意义：点的名字
    //返回值：无
    public Point(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    //getX、getY、getName:功能：读取三个属性，点建好之后不再改动，所以没写修改器
    //无入口参数
    //返回值：double、double、String，意义：横坐标、纵坐标、名字
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String getName() {
        return name;
    }
    //toString:功能：把点拼成"P0(2.10, 2.30)"这样，直接println就能看
    //无入口参数
    //返回值：String，意义：点的字符串表示
    public String toString() {
        return name + "(" + String.format("%.2f", x) + ", " + String.format("%.2f", y) + ")";
    }
}
