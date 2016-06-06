package tddmicroexercises.tirepressuremonitoringsystem;

import tddmicroexercises.tirepressuremonitoringsystem.sensor_types.PressureSensor;

public class Alarm {
    private final SafetyRange safetyRange;
    private Sensor sensor;
    private boolean alarmOn;

    public static Alarm createPressureAlarm() {
        final double LowPressureThreshold = 17;
        final double HighPressureThreshold = 21;
        return new Alarm(
            new PressureSensor(),
            new SafetyRange(LowPressureThreshold, HighPressureThreshold)
        );
    }

    public Alarm(Sensor sensor, SafetyRange safetyRange) {
        alarmOn = false;
        this.sensor = sensor;
        this.safetyRange = safetyRange;
    }

    public void check() {
        double value = probeValue();

        if (isNotSafe(value)) {
            activateAlarm();
        }
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }

    private void activateAlarm() {
        alarmOn = true;
    }

    private boolean isNotSafe(double value) {
        return !safetyRange.contains(value);
    }

    private double probeValue() {
        return sensor.probeValue();
    }
}
