import java.io.PrintWriter;
import java.util.List;

public class ClusterReportWriter {
    //write:功能：把聚类结果写成txt，一簇一段，列出质心、平均离差和簇内所有点
    //入口参数：String path，意义：输出文件路径；List<Cluster> cs，意义：分好的簇
    //返回值：void，意义：无返回
    public static void write(String path, List<Cluster> cs) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("==== K-Means 聚类结果 ====\n");
        for (int i = 0; i < cs.size(); i++) {
            Cluster c = cs.get(i);
            sb.append("簇").append(c.getId())
                    .append("  点数=").append(c.getPoints().size())
                    .append("  质心=(").append(String.format("%.3f", c.getCenter().getX()))
                    .append(", ").append(String.format("%.3f", c.getCenter().getY()))
                    .append(")  平均离差=").append(String.format("%.4f", c.avgDist()))
                    .append("\n");
            for (int j = 0; j < c.getPoints().size(); j++) {
                sb.append("    ").append(c.getPoints().get(j)).append("\n");
            }
            sb.append("\n");
        }
        PrintWriter out = new PrintWriter(path);
        out.print(sb);
        out.close();
    }
}
