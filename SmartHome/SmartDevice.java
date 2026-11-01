//① 抽象设备 SmartDevice
public abstract class SmartDevice {
    private final String deviceName;
    private boolean powered;

    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
        this.powered = false;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public boolean isPowered() {
        return powered;
    }

    protected void setPowered(boolean powered) {
        this.powered = powered;
    }

    public abstract void turnOn();

    public abstract void turnOff();
}
