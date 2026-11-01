//② 智能灯 SmartLight，继承 SmartDevice
public class SmartLight extends SmartDevice {
    private static final int MIN_BRIGHTNESS = 1;
    private static final int MAX_BRIGHTNESS = 100;
    private int brightness;

    public SmartLight(String deviceName) {
        super(deviceName);
        this.brightness = 60; // 默认亮度
    }
    @Override
    public void turnOn() {
        setPowered(true);
        System.out.println("[灯] " + getDeviceName() + " 已打开，亮度: " + brightness + "%");
    }

    @Override
    public void turnOff() {
        setPowered(false);
        System.out.println("[灯] " + getDeviceName() + " 已关闭");
    }
    public void setBrightness(int level) {
        if (level < MIN_BRIGHTNESS || level > MAX_BRIGHTNESS) {
            System.out.println("[灯] 亮度 " + level + "% 超出范围，请设置在 " + MIN_BRIGHTNESS + " - " + MAX_BRIGHTNESS + " 之间");
            return;
        }
        this.brightness = level;
        System.out.println("[灯] " + getDeviceName() + " 亮度已设置为: " + brightness + "%");
    }
}