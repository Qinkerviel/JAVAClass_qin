import java.util.ArrayList;
import java.util.List;

public class Main {
    //main:功能：同一批点，两种聚类各跑一遍，看结果对不对得上
    //入口参数：String[] args，意义：命令行参数，这里用不到
    //返回值：void，意义：无返回
    public static void main(String[] args) {
        //造两团数据：左下角四个点，右上角四个点，肉眼就能看出该分两簇
        List<Point> data = new ArrayList<>();
        data.add(new Point(1.0, 1.0, "P0"));
        data.add(new Point(1.5, 2.0, "P1"));
        data.add(new Point(2.0, 1.5, "P2"));
        data.add(new Point(2.5, 2.5, "P3"));
        data.add(new Point(8.0, 8.0, "P4"));
        data.add(new Point(8.5, 9.0, "P5"));
        data.add(new Point(9.0, 8.0, "P6"));
        data.add(new Point(9.5, 9.5, "P7"));

        //父类引用指向子类对象：同一个数组，同一个fit()，跑的却是两套完全不同的流程
        BaseClusterModel[] models = {new KMeansModel(2, 100), new HierarchicalModel(2)};
        String[] titles = {"K-Means（欧氏距离）", "层次聚类（曼哈顿距离）"};

        for (int i = 0; i < models.length; i++) {
            models[i].loadData(data);
            models[i].fit();
            models[i].printResult(titles[i]);
            System.out.println();
        }
    }
}
