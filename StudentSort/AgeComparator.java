import java.util.Comparator;

//按年龄排的策略。年龄一样的时候拿成绩降序兜底，
//不然同龄人谁前谁后每次跑可能都不一样。
public class AgeComparator implements Comparator<Student> {

    //compare:功能：先比年龄，年龄相同再比成绩
    //入口参数：Student a，意义：第一个学生；Student b，意义：第二个学生
    //返回值：int，意义：负数表示a排前面，正数表示a排后面
    @Override
    public int compare(Student a, Student b) {
        int result = Integer.compare(a.getAge(), b.getAge());
        if (result != 0) {
            return result;
        }
        return Double.compare(b.getScore(), a.getScore());   //同龄，分高的在前
    }
}
