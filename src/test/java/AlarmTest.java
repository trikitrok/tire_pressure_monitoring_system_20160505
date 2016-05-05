import org.junit.Test;
import tddmicroexercises.tirepressuremonitoringsystem.Alarm;
import tddmicroexercises.tirepressuremonitoringsystem.SafetyRange;
import tddmicroexercises.tirepressuremonitoringsystem.PressureSensor;
import tddmicroexercises.tirepressuremonitoringsystem.Sensor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlarmTest {
    @Test
    public void alarm_is_on_when_probed_value_is_too_low() {
        Alarm alarm = AlarmBuilder.anAlarm()
            .withSafetyRange(17.0, 21.0)
            .using(sensorThatProbes(5.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_off_when_probed_value_is_inside_safety_range() {
        Alarm alarm = AlarmBuilder.anAlarm()
            .withSafetyRange(17.0, 21.0)
            .using(sensorThatProbes(18.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(false));
    }

    @Test
    public void alarm_is_on_when_probed_value_is_too_high() {
        Alarm alarm = AlarmBuilder.anAlarm()
            .withSafetyRange(17.0, 21.0)
            .using(sensorThatProbes(30.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void once_the_alarm_is_on_it_keeps_on_regardless_of_new_probed_values() {
        Alarm alarm = AlarmBuilder.anAlarm()
            .withSafetyRange(17.0, 21.0)
            .using(sensorThatProbes(30.0, 18.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    protected PressureSensor sensorThatProbes(double value) {
        PressureSensor sensor = mock(PressureSensor.class);
        when(sensor.probeValue()).thenReturn(value);
        return sensor;
    }

    protected PressureSensor sensorThatProbes(double value1, double value2) {
        PressureSensor sensor = mock(PressureSensor.class);
        when(sensor.probeValue()).thenReturn(value1, value2);
        return sensor;
    }

    static class AlarmBuilder {
        private SafetyRange safetyRange;
        private Sensor sensor;

        public static AlarmBuilder anAlarm() {
            return new AlarmBuilder();
        }

        public AlarmBuilder withSafetyRange(double lowerThreshold, double higherThreshold) {
            this.safetyRange = new SafetyRange(lowerThreshold, higherThreshold);
            return this;
        }

        public AlarmBuilder using(Sensor sensor) {
            this.sensor = sensor;
            return this;
        }

        public Alarm build() {
            return new Alarm(sensor, safetyRange);
        }
    }
}
