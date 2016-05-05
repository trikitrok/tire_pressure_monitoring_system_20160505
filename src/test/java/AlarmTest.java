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

    class FakeAlarm extends Alarm {
        private final double probedValue;

        public FakeAlarm(double probedValue) {
            this.probedValue = probedValue;
        }

        @Override
        protected double probePressureValue() {
            return probedValue;
        }
    }
}
