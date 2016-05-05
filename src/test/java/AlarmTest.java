import org.junit.Test;
import tddmicroexercises.tirepressuremonitoringsystem.Alarm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AlarmTest {
    @Test
    public void alarm_is_on_when_pressure_is_too_low() {
        Alarm alarm = new FakeAlarm(5.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void alarm_is_off_when_pressure_is_inside_safety_range() {
        Alarm alarm = new FakeAlarm(18.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(false));
    }

    @Test
    public void alarm_is_on_when_pressure_is_too_high() {
        Alarm alarm = new FakeAlarm(30.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    @Test
    public void once_the_alarm_is_on_it_keeps_on_regardless_new_probed_values() {
        Alarm alarm = new FakeAlarm(30.0, 18.0);

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));

        alarm.check();

        assertThat(alarm.isAlarmOn(), is(true));
    }

    class FakeAlarm extends Alarm {
        private final double[] probedValues;
        private int index;

        public FakeAlarm(double ... probedValue) {
            this.probedValues = probedValue;
            this.index = 0;
        }

        @Override
        protected double probePressureValue() {
            double probedValue = probedValues[index];
            index++;
            return probedValue;
        }
    }
}
