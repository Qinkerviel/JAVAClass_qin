import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    //main:功能：一批学生，按不同规则反复排，想看哪种排法就选哪种
    //入口参数：String[] args，意义：命令行参数，这里用不到
    //返回值：void，意义：无返回
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("2025007501", "秦伟洁", 92.5, 19));
        list.add(new Student("2025007503", "李思远", 78.0, 20));
        list.add(new Student("2025007502", "王晨曦", 88.5, 18));
        list.add(new Student("2025007506", "周雨桐", 95.0, 19));
        list.add(new Student("2025007504", "赵子航", 65.5, 21));
        list.add(new Student("2025007505", "孙嘉宁", 88.5, 20));

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("学号\t\t姓名\t成绩\t年龄");
            printList(list);
            System.out.print("排序方式 1学号升序 2成绩降序 3成绩升序 4姓名升序 5年龄升序 0退出：");
            int choice = sc.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    //不传比较器，走Student里写好的compareTo，这就是自然排序
                    Collections.sort(list);
                    break;
                case 2:
                    //传一个比较器进去，排序规则临时定，这就是外部策略排序
                    list.sort(new ScoreComparator(true));
                    break;
                case 3:
                    list.sort(new ScoreComparator(false));
                    break;
                case 4:
                    list.sort(new NameComparator());
                    break;
                case 5:
                    list.sort(new AgeComparator());
                    break;
                default:
                    System.out.println("没有这个选项");
            }
            System.out.println();
        }
        sc.close();
    }

    //printList:功能：把学生一行一个打出来
    //入口参数：List<Student> list，意义：要打印的学生列表
    //返回值：void，意义：无返回
    public static void printList(List<Student> list) {
        for (Student s : list) {
            System.out.println(s);
        }
    }
}
