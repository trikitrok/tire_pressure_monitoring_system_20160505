package helpers;

import tddmicroexercises.tirepressuremonitoringsystem.Alarm;
import tddmicroexercises.tirepressuremonitoringsystem.SafetyRange;
import tddmicroexercises.tirepressuremonitoringsystem.Sensor;

public class AlarmBuilder {
    private SafetyRange safetyRange;
    private Sensor sensor;

    public static AlarmBuilder anAlarm() {
        return new AlarmBuilder();
    }

    public AlarmBuilder withSafetyRange(double lowerThreshold, double higherThreshold) {
        safetyRange = new SafetyRange(lowerThreshold, higherThreshold);
        return this;
    }

    public AlarmBuilder usingSensor(Sensor sensor) {
        this.sensor = sensor;
        return this;
    }

    public Alarm build() {
        return new Alarm(sensor, safetyRange);
    }
}
