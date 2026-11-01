//② 智能空调 SmartAC，继承 SmartDevice
public class SmartAC extends SmartDevice {
    private static final int MIN_TEMP = 16;
    private static final int MAX_TEMP = 30;
    private int temperature;

    public SmartAC(String deviceName) {
        super(deviceName);
        this.temperature = 26; // 默认温度
    }

    @Override
    public void turnOn() {
        setPowered(true);
        System.out.println("[空调] " + getDeviceName() + " 已启动，设定温度: " + temperature + "℃");
    }

    @Override
    public void turnOff() {
        setPowered(false);
        System.out.println("[空调] " + getDeviceName() + " 已关闭");
    }

    public void setTemperature(int temp) {
        if (temp < MIN_TEMP || temp > MAX_TEMP) {
            System.out.println("[空调] 温度 " + temp + "℃ 超出范围，请设置在 " + MIN_TEMP + " - " + MAX_TEMP + " 之间");
            return;
        }
        this.temperature = temp;
        System.out.println("[空调] " + getDeviceName() + " 温度已设置为: " + temperature + "℃");
    }
}
