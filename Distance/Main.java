public class Main {
    //main:功能：同一个接口，四种距离实现，看结果怎么变
    //入口参数：String[] args，意义：命令行参数，这里用不到
    //返回值：void，意义：无返回
    public static void main(String[] args) {
        //重点是下面两行：左边是接口类型，右边是具体实现
        //换成谁，calculate跑的就是谁的公式，Main一行都不用改
        String[] names = {"欧氏距离", "曼哈顿距离", "切比雪夫距离", "加权欧氏距离(0.3, 0.7)"};
        DistanceMetric[] metrics = {
            Metrics.EUCLIDEAN,
            Metrics.MANHATTAN,
            Metrics.CHEBYSHEV,
            new WeightedEuclideanDistance(0.3, 0.7)
        };

        //第一对点：横3竖4，正好是3-4-5直角三角形，欧氏距离是整数好验算
        Point p1 = new Point(0, 0, "P1");
        Point p2 = new Point(3, 4, "P2");
        printAll(p1, p2, names, metrics);

        //第二对点：横6竖1，斜着不多，竖着很少，四种距离的排序会跟着变
        Point p3 = new Point(0, 0, "P3");
        Point p4 = new Point(6, 1, "P4");
        printAll(p3, p4, names, metrics);
    }

    //printAll:功能：把一对点在每种距离下的结果都打出来
    //入口参数：Point a，意义：第一个点；Point b，意义：第二个点
    //String[] names，意义：各距离的名字；DistanceMetric[] metrics，意义：各距离的实现
    //返回值：void，意义：无返回
    public static void printAll(Point a, Point b, String[] names, DistanceMetric[] metrics) {
        System.out.println(a + " 到 " + b + "：");
        for (int i = 0; i < metrics.length; i++) {
            //这一句是全场唯一一句调用，走的公式却由metrics[i]的真实类型决定
            double d = metrics[i].calculate(a, b);
            System.out.println("  " + names[i] + " = " + String.format("%.4f", d));
        }
        System.out.println();
    }
}
