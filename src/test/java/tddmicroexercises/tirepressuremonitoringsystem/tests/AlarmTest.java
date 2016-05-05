package tddmicroexercises.tirepressuremonitoringsystem.tests;

import org.junit.Test;
import tddmicroexercises.tirepressuremonitoringsystem.Alarm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static tddmicroexercises.tirepressuremonitoringsystem.tests.helpers.AlarmBuilder.anAlarm;
import static tddmicroexercises.tirepressuremonitoringsystem.tests.helpers.SensorFactory.sensorThatProbes;

public class AlarmTest {
    @Test
    public void alarm_is_on_when_probed_value_is_too_low() {
        Alarm alarm = anAlarm()
            .withSafetyRange(17.0, 21.0)
            .using(sensorThatProbes(5.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_off_when_probed_value_is_inside_safety_range() {
        Alarm alarm = anAlarm()
            .withSafetyRange(17.0, 21.0)
            .using(sensorThatProbes(18.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(false));
    }

    @Test
    public void alarm_is_on_when_probed_value_is_too_high() {
        Alarm alarm = anAlarm()
            .withSafetyRange(17.0, 21.0)
            .using(sensorThatProbes(30.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void once_the_alarm_is_on_it_keeps_on_regardless_of_new_probed_values() {
        Alarm alarm = anAlarm()
            .withSafetyRange(17.0, 21.0)
            .using(sensorThatProbes(30.0, 18.0)).build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }
}
