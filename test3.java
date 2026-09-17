import java.util.Random;
import java.util.Scanner;
public class test3 {
    //main:功能：随机加减乘除运算入口
    //入口参数：String[] args，意义：命令行参数
    //返回值：void，意义：无返回
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("算几次？");
        int total = sc.nextInt();
        int correctCount = 0;
        for (int i = 1 ; i <= total; i++) {
            int type = rand.nextInt(4);
            String[] qa = createQuestion(type,rand);
            String question = qa[0];
            int answer = Integer.parseInt(qa[1]);
            System.out.print("第" + i + "题：" + question + " = ");
            int input = sc.nextInt();
            if (input == answer) {
                System.out.println("You are clever!");
                correctCount++;
            }else {
                System.out.println("You are flase! The answer is " + answer);
            }
        }
        System.out.println("正确率：" + (double) correctCount / total * 100 + "%");
        sc.close();
    }
    //createQuestion:功能：生成随机加减乘除运算题目,并返回题目和答案
    //入口参数：int type，意义：题目类型（0加 1减 2乘 3除）
    //Random rand，意义：随机数生成器
    //返回值：String[]，意义：题目和答案
    public static String[] createQuestion(int type, Random rand) {
        int a, b, answer;
        char operator;
        if (type == 0) {
           a = rand.nextInt(50) + 1;
           b = rand.nextInt(50) + 1;
           operator = '+';
           answer = a + b;
           } else if (type == 1) {
              a = rand.nextInt(50) + 1;
              b = rand.nextInt(50) + 1;
              if (a < b) {
                  int temp = a;
                  a = b;
                  b = temp;
              }
                operator = '-';
                answer = a - b;
        } else if (type == 2) {
            a = rand.nextInt(12) + 1;
            b = rand.nextInt(12) + 1;
            operator = '*';
            answer = a * b;
        } else {
            b = rand.nextInt(12) + 1;
            answer = rand.nextInt(12) + 1;
            a = answer * b;
            operator = '/';
        }
        String question = a + " " + operator + " " + b;
        return new String[]{question, String.valueOf(answer)};
    }
}