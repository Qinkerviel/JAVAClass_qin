public class Point {
    private double x;
    private double y;
    private String id;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.id = "";
    }
    public Point(double x, double y, String id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    //欧式距离：当前点到另一个点
    public double distanceTo(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return String.format("%s(%.2f,%.2f)",id.isEmpty() ? "point" : id, x, y);
    }
}
