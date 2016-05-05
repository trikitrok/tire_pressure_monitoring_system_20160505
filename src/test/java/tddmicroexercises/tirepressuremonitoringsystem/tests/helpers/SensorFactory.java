package tddmicroexercises.tirepressuremonitoringsystem.tests.helpers;

import tddmicroexercises.tirepressuremonitoringsystem.PressureSensor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SensorFactory {
    public static PressureSensor sensorThatProbes(double value) {
        PressureSensor sensor = mock(PressureSensor.class);
        when(sensor.probeValue()).thenReturn(value);
        return sensor;
    }

    public static PressureSensor sensorThatProbes(double value1, double value2) {
        PressureSensor sensor = mock(PressureSensor.class);
        when(sensor.probeValue()).thenReturn(value1, value2);
        return sensor;
    }
}
