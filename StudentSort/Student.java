//学生类。implements Comparable<Student>是因为"按学号排"是学生天生就有的顺序，
//实现之后 Collections.sort(list) 不用传任何东西，直接按学号从小到大。
public class Student implements Comparable<Student> {
    //属性：id学号、name姓名、score成绩、age年龄
    private String id;      //学号用String存，因为真实学号可能带字母，也可能有前导0
    private String name;
    private double score;
    private int age;

    //Student:功能：造一个学生
    //入口参数：String id，意义：学号；String name，意义：姓名
    //double score，意义：成绩；int age，意义：年龄
    //返回值：无
    public Student(String id, String name, double score, int age) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.age = age;
    }

    //getId、getName、getScore、getAge:功能：读属性，排序策略在类外面，必须能拿到值
    //无入口参数
    //返回值：String、String、double、int，意义：学号、姓名、成绩、年龄
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getScore() {
        return score;
    }
    public int getAge() {
        return age;
    }

    //compareTo:功能：定"自然顺序"，也就是不指定规则时默认怎么排，这里定成学号升序
    //入口参数：Student other，意义：要比较的另一个学生
    //返回值：int，意义：负数表示我排前面，正数表示我排后面，0表示一样大
    @Override
    public int compareTo(Student other) {
        int result = this.id.compareTo(other.id);
        if (result != 0) {
            return result;   //学号不同，就按学号定
        }
        return this.name.compareTo(other.name);   //学号一样（理论上不会有），拿姓名兜底
    }

    //toString:功能：拼成一行，中间用制表符隔开，打印出来像张表
    //无入口参数
    //返回值：String，意义：学生的字符串表示
    @Override
    public String toString() {
        return id + "\t" + name + "\t" + String.format("%.1f", score) + "\t" + age;
    }
}
