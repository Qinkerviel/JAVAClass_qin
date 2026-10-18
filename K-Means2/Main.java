import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    //main:功能：读入点，三种距离各跑一遍K-Means，打印并各写一个结果文件，最后对比点的归属
    //入口参数：String[] args，意义：命令行参数，可传簇数K，不传默认K=3
    //返回值：void，意义：无返回
    public static void main(String[] args) throws Exception {
        List<Point> data = PointFileReader.read("input_points.txt");
        System.out.println("读入点数：" + data.size());

        //簇数从命令行拿，不传就按3。演示换度量的时候一般不用动这里
        int k = 3;
        if (args.length > 0) {
            k = Integer.parseInt(args[0]);
        }

        //KMeans一行都不用动，换度量只是把这里传进去的对象换掉
        List<Cluster> euc = run("欧氏距离", Metrics.EUCLIDEAN, k, data, "cluster_euclidean.txt");
        List<Cluster> man = run("曼哈顿距离", Metrics.MANHATTAN, k, data, "cluster_manhattan.txt");
        List<Cluster> che = run("切比雪夫距离", Metrics.CHEBYSHEV, k, data, "cluster_chebyshev.txt");

        //以欧氏距离为基准，看换了度量以后有没有点跑到别的簇去了
        Map<String, Integer> base = assign(euc);
        compare("曼哈顿距离", base, assign(man));
        compare("切比雪夫距离", base, assign(che));
    }
    //run:功能：用一种距离跑一次聚类，打印结果并写出文件
    //入口参数：String label，意义：中文名字，打印用；DistanceMetric m，意义：距离度量；
    //         int k，意义：簇数；List<Point> data，意义：数据点；String out，意义：输出文件名
    //返回值：List<Cluster>，意义：这次分出来的簇
    private static List<Cluster> run(String label, DistanceMetric m, int k, List<Point> data, String out) throws Exception {
        System.out.println();
        System.out.println("=== " + label + "（" + m.getClass().getSimpleName() + "）===");
        KMeans km = new KMeans(k, 100, 0.0001, m);
        List<Cluster> cs = km.fit(data);
        System.out.println("K=" + k + "，一共迭代 " + km.getIterations() + " 轮");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.size(); i++) {
            Cluster c = cs.get(i);
            sb.append("簇").append(c.getId()).append("：")
              .append(c.getPoints().size()).append(" 个点，质心(")
              .append(String.format("%.2f", c.getCenter().getX())).append(", ")
              .append(String.format("%.2f", c.getCenter().getY())).append(")")
              .append("，平均离差 ").append(String.format("%.4f", c.avgDist(m)))
              .append("\n");
        }
        System.out.print(sb);

        ClusterReportWriter.write(out, cs, m);
        System.out.println("结果已写入 " + out);
        return cs;
    }
    //assign:功能：把"点名字 -> 簇编号"整理成一张表，方便比较两次聚类
    //入口参数：List<Cluster> cs，意义：分好的簇
    //返回值：Map<String, Integer>，意义：键是点名字，值是它所在的簇编号
    private static Map<String, Integer> assign(List<Cluster> cs) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < cs.size(); i++) {
            Cluster c = cs.get(i);
            for (int j = 0; j < c.getPoints().size(); j++) {
                Point p = c.getPoints().get(j);
                map.put(p.getName(), c.getId());
            }
        }
        return map;
    }
    //compare:功能：拿另一种度量的结果跟基准比，数一数有多少个点换簇了
    //入口参数：String label，意义：对比的那个度量叫什么；Map base，意义：基准；Map other，意义：另一种度量的结果
    //返回值：void，意义：无返回
    private static void compare(String label, Map<String, Integer> base, Map<String, Integer> other) {
        int changed = 0;
        for (String name : base.keySet()) {
            if (!base.get(name).equals(other.get(name))) {
                changed++;
            }
        }
        System.out.println(label + "：有 " + changed + " 个点换簇了");
    }
}
