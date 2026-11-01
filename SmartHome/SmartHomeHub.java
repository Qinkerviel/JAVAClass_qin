//③ 智能音箱控制中心 SmartHomeHub
public class SmartHomeHub {

    public void controlDevice(SmartDevice device) {
        device.turnOn(); // 多态：一句 turnOn 走所有子类，加设备不用改这里

        if (device instanceof SmartLight light) {
            light.setBrightness(80);
        } else if (device instanceof SmartAC ac) {
            ac.setTemperature(26);
        }
    }
}
