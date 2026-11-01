//④ 测试入口
public class Main {
    public static void main(String[] args) {
        SmartHomeHub hub = new SmartHomeHub();

        SmartDevice[] devices = {
            new SmartLight("客厅主灯"),
            new SmartLight("书房台灯"),
            new SmartAC("卧室空调")
        };

        for (SmartDevice d : devices) {
            hub.controlDevice(d);
        }

        System.out.println("---- 统一关机 ----");
        for (SmartDevice d : devices) {
            d.turnOff();
        }
    }
}
