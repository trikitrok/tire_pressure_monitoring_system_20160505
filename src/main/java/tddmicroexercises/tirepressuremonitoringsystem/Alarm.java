package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm {

    private final SafetyRange safetyRange;

    private Sensor sensor;
    private boolean alarmOn;

    public static Alarm createTelemetryPressureAlarm() {
        final double LowPressureThreshold = 17;
        final double HighPressureThreshold = 21;
        return new Alarm(
            new TelemetryPressureSensor(),
            new SafetyRange(LowPressureThreshold, HighPressureThreshold)
        );
    }

    public Alarm(Sensor sensor, SafetyRange safetyRange) {
        this.sensor = sensor;
        alarmOn = false;
        this.safetyRange = safetyRange;
    }

    public void check() {
        double probedValue = sensor.probeValue();
        if (isNotSafe(probedValue)) {
            activateAlarm();
        }
    }

    protected void activateAlarm() {
        alarmOn = true;
    }

    protected boolean isNotSafe(double value) {
        return !safetyRange.contains(value);
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
