package tddmicroexercises.tirepressuremonitoringsystem;

public class SomeAlarmClient {

    public static void main(String[] args) {
        someCodeUsingAlarm();
        moreCodeUsingAlarm();
    }

    private static void someCodeUsingAlarm() {
        Alarm alarm = Alarm.createTelemetryPressureAlarm();
        alarm.check();
        System.out.println("Some code using " + alarm.isAlarmOn());
    }

    private static void moreCodeUsingAlarm() {
        Alarm alarm = Alarm.createTelemetryPressureAlarm();
        alarm.check();
        System.out.println("Other code using " + alarm.isAlarmOn());
    }
}
