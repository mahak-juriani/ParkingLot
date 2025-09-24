
import java.time.Duration;

public interface PricingStrategy {
    double calculateAmount(Duration duration, VehicleType type);
}