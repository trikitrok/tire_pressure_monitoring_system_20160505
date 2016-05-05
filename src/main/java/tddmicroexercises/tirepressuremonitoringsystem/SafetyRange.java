package tddmicroexercises.tirepressuremonitoringsystem;

public class SafetyRange {
    private final double lowerThreshold;
    private final double higherThreshold;

    public SafetyRange(double lowerThreshold, double higherThreshold) {
        this.lowerThreshold = lowerThreshold;
        this.higherThreshold = higherThreshold;
    }

    public boolean contains(double value) {
        return lowerThreshold <= value && value <= higherThreshold;
    }
}