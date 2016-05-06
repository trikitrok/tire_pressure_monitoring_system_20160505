package tddmicroexercises.tirepressuremonitoringsystem.tests;

import org.junit.Test;
import tddmicroexercises.tirepressuremonitoringsystem.Alarm;
import tddmicroexercises.tirepressuremonitoringsystem.Sensor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tddmicroexercises.tirepressuremonitoringsystem.tests.helpers.AlarmBuilder.anAlarm;

public class AlarmTest {
    @Test
    public void alarm_is_on_when_probed_value_is_too_low() {
        Alarm alarm = anAlarm()
            .withSafetyRange(6.0, 8.0)
            .using(sensorProbing(5.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_off_when_probed_value_is_inside_safety_range() {
        Alarm alarm = anAlarm()
            .withSafetyRange(15.0, 20.0)
            .using(sensorProbing(18.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(false));
    }

    @Test
    public void alarm_is_on_when_probed_value_is_too_high() {
        Alarm alarm = anAlarm()
            .withSafetyRange(10.0, 30.0)
            .using(sensorProbing(50.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void once_alarm_is_on_it_keeps_on_regardless_of_any_probed_value() {
        Sensor sensor = mock(Sensor.class);
        when(sensor.probeValue()).thenReturn(50.0, 18.0);
        Alarm alarm = anAlarm()
            .withSafetyRange(17, 21)
            .using(sensor).build();
        alarm.check();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    private Sensor sensorProbing(double value) {
        Sensor sensor = mock(Sensor.class);
        when(sensor.probeValue()).thenReturn(value);
        return sensor;
    }
}
