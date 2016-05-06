package tddmicroexercises.tirepressuremonitoringsystem.tests.helpers;

import tddmicroexercises.tirepressuremonitoringsystem.Alarm;
import tddmicroexercises.tirepressuremonitoringsystem.SafetyRange;
import tddmicroexercises.tirepressuremonitoringsystem.Sensor;

public class AlarmBuilder {

    private Sensor sensor;
    private SafetyRange safetyRange;

    public static AlarmBuilder anAlarm() {
        return new AlarmBuilder();
    }

    public AlarmBuilder using(Sensor sensor) {
        this.sensor = sensor;
        return this;
    }

    public AlarmBuilder withSafetyRange(double lowThreshold, double highThreshold) {
        this.safetyRange = new SafetyRange(lowThreshold, highThreshold);
        return this;
    }

    public Alarm build() {
        return new Alarm(sensor, safetyRange);
    }
}
