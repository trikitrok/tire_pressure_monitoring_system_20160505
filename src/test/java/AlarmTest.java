import org.junit.Test;
import tddmicroexercises.tirepressuremonitoringsystem.Alarm;
import tddmicroexercises.tirepressuremonitoringsystem.Sensor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlarmTest {
    @Test
    public void alarm_is_on_when_pressure_is_too_low() {
        Sensor sensor = mock(Sensor.class);
        doReturn(5.0).when(sensor).popNextPressurePsiValue();
        Alarm alarm = new Alarm(sensor);

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

    @Test
    public void alarm_collaborates_with_injected_sensor() {
        Sensor sensor = mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);

        alarm.check();

        verify(sensor).popNextPressurePsiValue();
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
