public class Metrics {
    //三个实现类都没有成员变量，算出来的结果只跟传进去的点有关，
    //所以做一个实例放着共用就行，不用每轮迭代都new一个
    public static final DistanceMetric EUCLIDEAN = new EuclideanDistance();
    public static final DistanceMetric MANHATTAN = new ManhattanDistance();
    public static final DistanceMetric CHEBYSHEV = new ChebyshevDistance();

    //Metrics:功能：这个类只是拿来放常量的，不给外面new，构造方法写成private
    //入口参数：无
    //返回值：无
    private Metrics() {
    }
}
