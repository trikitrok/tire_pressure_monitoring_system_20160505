package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm {
    private final double LowPressureThreshold = 17;
    private final double HighPressureThreshold = 21;

    private Sensor sensor = new Sensor();

    private boolean alarmOn = false;

    public Alarm() {

    }

    public Alarm(Sensor sensor) {
        this.sensor = sensor;
    }

    public void check() {
        double psiPressureValue = probePressureValue();

        if (isNotSafe(psiPressureValue)) {
            activateAlarm();
        }
    }

    protected void activateAlarm() {
        alarmOn = true;
    }

    protected boolean isNotSafe(double psiPressureValue) {
        return psiPressureValue < LowPressureThreshold || HighPressureThreshold < psiPressureValue;
    }

    protected double probePressureValue() {
        return sensor.popNextPressurePsiValue();
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
