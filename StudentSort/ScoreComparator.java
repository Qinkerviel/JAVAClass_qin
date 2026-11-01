import java.util.Comparator;

//按成绩排的策略。升降序只差一个正负号，所以放个开关共用一个类，不用写两遍。
//Comparator是"别人帮我排"，排法写在学生类外面，想换就换，学生类一行不用动。
public class ScoreComparator implements Comparator<Student> {
    //属性：desc为true是降序（分高的在前），false是升序
    private boolean desc;

    //ScoreComparator:功能：记下要降序还是要升序
    //入口参数：boolean desc，意义：true降序，false升序
    //返回值：无
    public ScoreComparator(boolean desc) {
        this.desc = desc;
    }

    //compare:功能：比较两个学生的成绩
    //入口参数：Student a，意义：第一个学生；Student b，意义：第二个学生
    //返回值：int，意义：负数表示a排前面，正数表示a排后面，0表示成绩一样
    @Override
    public int compare(Student a, Student b) {
        int result = Double.compare(a.getScore(), b.getScore());
        if (desc) {
            return -result;   //降序就是反过来
        }
        return result;
    }
}
