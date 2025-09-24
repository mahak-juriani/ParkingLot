import java.time.Duration;
import java.util.EnumMap;
import java.util.Map;

public class SimplePricing implements PricingStrategy {
    private final Map<VehicleType, Double> perHour = new EnumMap<>(VehicleType.class);

    public SimplePricing() {
        perHour.put(VehicleType.BIKE, 20.0);
        perHour.put(VehicleType.CAR,  40.0);
        perHour.put(VehicleType.BUS,  80.0);
    }

    @Override
    public double calculateAmount(Duration duration, VehicleType type) {
        long minutes = Math.max(1, duration.toMinutes());
        long hours = (minutes + 59) / 60; // ceil
        return perHour.getOrDefault(type, 50.0) * hours;
    }
}