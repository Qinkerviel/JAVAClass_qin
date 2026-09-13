import java.util.Scanner;

public class test1 {
    //question 1.3:功能：打印JAVA图案
    //无入口参数
    //返回值：void
    public static void question1_3() {
        System.out.println("    J    A   V     V  A");
        System.out.println("    J   A A   V   V  A A");
        System.out.println("J   J  AAAAA   V V  AAAAA");
        System.out.println(" J J  A     A   V  A     A");
    }
    //question 1.4:功能：循环输出1~4对应的a、a平方、a立方表格
    //无入口参数
    //返回值：void，意义：无返回
    public static void question1_4(){
        System.out.println("a   a^2   a^3");
        for (int a = 1; a <= 4; a++) {
            System.out.println(a + "   " + (a * a) + "   " + (a * a * a));
        }
    }
    //question 1.7:功能：用莱布里茨公式计算π的近似值
    //入口参数：int n，意义：计算π的近似值时的迭代次数
    //返回值：double，意义：π的近似值
    public static double question1_7(int n) {
        double pi = 0.0;
        int j = 1;
        for(int i = 0; i < n; i+= 2){
            pi += (double)j / (i + 1);
            j = -j;
        }  
        return pi * 4;
    }
    //main:功能：程序入口，执行三道题目，读取用户输入并调用each question method
    //入口参数：String[] args，意义：命令行参数
    //返回值：void，意义：无返回
    public static void main(String[] args) {
        question1_3();
        System.out.println();
        question1_4();
        System.out.println();
        int n;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("请输入迭代次数：");
        n = sc.nextInt();
    }
        double pi = question1_7(n);
        System.out.println("π的近似值 = " + pi);
}
    
}