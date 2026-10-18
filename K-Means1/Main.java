import java.util.List;

public class Main {
    //main:功能：读入点，调用KMeans聚类，打印结果并写出结果文件
    //入口参数：String[] args，意义：命令行参数，可传簇数K，不传默认K=3
    //返回值：void，意义：无返回
    public static void main(String[] args) throws Exception {
        int k = 3;
        if (args.length > 0) {
            k = Integer.parseInt(args[0]);
        }

        List<Point> data = PointFileReader.read("input_points.txt");
        System.out.println("读入点数：" + data.size());

        KMeans km = new KMeans(k, 100, 0.0001);
        List<Cluster> cs = km.fit(data);

        System.out.println("K=" + k + "，一共迭代 " + km.getIterations() + " 轮");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.size(); i++) {
            Cluster c = cs.get(i);
            sb.append("簇").append(c.getId()).append("：")
              .append(c.getPoints().size()).append(" 个点，质心(")
              .append(String.format("%.2f", c.getCenter().getX())).append(", ")
              .append(String.format("%.2f", c.getCenter().getY())).append(")")
              .append("，平均离差 ").append(String.format("%.4f", c.avgDist()))
              .append("\n");
        }
        System.out.print(sb);

        ClusterReportWriter.write("cluster_results.txt", cs);
        System.out.println("结果已写入 cluster_results.txt");
    }
}
