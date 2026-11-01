import java.util.Comparator;

//按姓名排的策略。注意String.compareTo比的是字符编码顺序，中文不是按拼音来的，
//要按拼音得用Collator，这里先用compareTo，够看效果。
public class NameComparator implements Comparator<Student> {

    //compare:功能：比较两个学生的姓名
    //入口参数：Student a，意义：第一个学生；Student b，意义：第二个学生
    //返回值：int，意义：负数表示a排前面，正数表示a排后面
    @Override
    public int compare(Student a, Student b) {
        return a.getName().compareTo(b.getName());
    }
}
