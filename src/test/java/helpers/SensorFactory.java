package helpers;

import tddmicroexercises.tirepressuremonitoringsystem.Sensor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SensorFactory {

    public static Sensor thatProbes(double value) {
        Sensor sensor = mock(Sensor.class);
        when(sensor.probeValue()).thenReturn(value);
        return sensor;
    }

    public static Sensor thatProbes(double value1, double value2) {
        Sensor sensor = mock(Sensor.class);
        when(sensor.probeValue()).thenReturn(value1, value2);
        return sensor;
    }
}
