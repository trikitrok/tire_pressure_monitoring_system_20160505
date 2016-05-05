package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm {
    private final double LowPressureThreshold = 17;
    private final double HighPressureThreshold = 21;
    private final SafetyRange safetyRange;
    private Sensor sensor;
    private boolean alarmOn;

    public Alarm() {
        this(new Sensor());
    }

    public Alarm(Sensor sensor) {
        alarmOn = false;
        this.sensor = sensor;
        safetyRange = new SafetyRange(LowPressureThreshold, HighPressureThreshold);
    }

    public void check() {
        double value = probePressureValue();

        if (isNotSafe(value)) {
            activateAlarm();
        }
    }

    protected void activateAlarm() {
        alarmOn = true;
    }

    protected boolean isNotSafe(double psiPressureValue) {
        return ! safetyRange.contains(psiPressureValue);
    }

    protected double probePressureValue() {
        return sensor.popNextPressurePsiValue();
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }

    class SafetyRange {
        private final double lowerThreshold;
        private final double higherThreshold;

        public SafetyRange(double lowerThreshold, double higherThreshold) {
            this.lowerThreshold = lowerThreshold;
            this.higherThreshold = higherThreshold;
        }

        public boolean contains(double value) {
            return !(value < lowerThreshold || higherThreshold < value);
        }
    }
}
