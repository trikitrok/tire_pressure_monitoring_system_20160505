package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm {
    private final SafetyRange safetyRange;
    private Sensor sensor;
    private boolean alarmOn;

    public static Alarm createPressureAlarm() {
        final double LowPressureThreshold = 17;
        final double HighPressureThreshold = 21;
        return new Alarm(
            new Sensor(),
            new SafetyRange(LowPressureThreshold, HighPressureThreshold)
        );
    }

    public Alarm(Sensor sensor, SafetyRange safetyRange) {
        alarmOn = false;
        this.sensor = sensor;
        this.safetyRange = safetyRange;
    }

    public void check() {
        double value = probePressureValue();

        if (isNotSafe(value)) {
            activateAlarm();
        }
    }

    protected void activateAlarm() {
        alarmOn = true;
    }

    protected boolean isNotSafe(double value) {
        return ! safetyRange.contains(value);
    }

    protected double probePressureValue() {
        return sensor.popNextPressurePsiValue();
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
