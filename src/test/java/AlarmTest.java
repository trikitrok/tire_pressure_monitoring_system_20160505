import org.junit.Test;
import tddmicroexercises.tirepressuremonitoringsystem.Alarm;

import static helpers.AlarmBuilder.anAlarm;
import static helpers.SensorFactory.thatProbes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AlarmTest {
    @Test
    public void alarm_is_on_when_probed_value_is_too_low() {
        Alarm alarm = anAlarm()
            .withSafetyRange(17, 21)
            .usingSensor(thatProbes(5.0))
            .build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_off_when_probed_value_is_inside_safety_range() {
        Alarm alarm = anAlarm()
            .withSafetyRange(17, 21)
            .usingSensor(thatProbes(18.0))
            .build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(false));
    }

    @Test
    public void alarm_is_on_when_probed_value_is_too_high() {
        Alarm alarm = anAlarm()
            .withSafetyRange(17, 21)
            .usingSensor(thatProbes(30.0))
            .build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void once_the_alarm_is_on_it_keeps_on_regardless_of_new_probed_values() {
        Alarm alarm = anAlarm()
            .withSafetyRange(17, 21)
            .usingSensor(thatProbes(30.0, 18.0))
            .build();

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }
}
