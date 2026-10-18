import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointFileReader {
    //read:功能：从txt里读点，一行两个数，中间用空格或者制表符隔开
    //入口参数：String path，意义：文件路径
    //返回值：List<Point>，意义：读出来的点，名字按顺序编成P0、P1……
    public static List<Point> read(String path) throws Exception {
        List<Point> list = new ArrayList<>();
        Scanner sc = new Scanner(new File(path));
        int i = 0;
        while (sc.hasNextDouble()) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            list.add(new Point(x, y, "P" + i));
            i++;
        }
        sc.close();
        return list;
    }
}
