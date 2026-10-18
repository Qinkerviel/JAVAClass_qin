//距离度量的接口。接口里只写"能做什么"，不写"怎么做"，
//谁implements它，谁就得把calculate写出来。KMeans只认这个接口，不认具体的公式。
public interface DistanceMetric {

    //calculate:功能：计算两个点之间的距离
    //入口参数：Point p1，意义：第一个点；Point p2，意义：第二个点
    //返回值：double，意义：两点之间的距离，具体是欧氏还是曼哈顿由实现类决定
    double calculate(Point p1, Point p2);
}
