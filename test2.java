import java.util.Scanner;
public class test2 {
    //main:功能：程序入口，读取用户输入输出找零结果
    //入口参数：String[] args，意义：命令行参数
    //返回值：void，意义：无返回
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("价格：");
        double price = sc.nextDouble();
        System.out.print("实付：");
        double pay = sc.nextDouble();
        String result = calculateChange(price, pay);
        System.out.println(result);
        sc.close();
}
//calculateChange:功能：计算找零金额及其面额
//double price，意义：商品价格
//double pay，意义：实付金额
//返回值：String，意义：找零金额及其面额
public static String calculateChange(double price, double pay) {
    if (pay < price) {
        return "钱不够";
    }
    double change = pay - price;
    if (change == 0) {
        return "无需找零";
    }
    int fen = (int) Math.round(change * 100);
    int[] values = {10000, 5000, 2000, 1000, 500, 200, 100};
    String[] labels = {"100元", "50元", "20元", "10元", "5元", "2元", "1元"};
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < values.length; i++) {
        int count = fen / values[i];
        if (count > 0) {
            sb.append(count).append("张").append(labels[i]).append(" ");
            fen %= values[i];
        }
    }
    if (fen > 0) {
        sb.append(String.format("剩余1 %.2f 元", fen / 100.0));
    }
    return sb.toString();
    }
}

    