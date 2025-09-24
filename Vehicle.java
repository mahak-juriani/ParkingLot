
import java.util.Objects;

public final class Vehicle {
    private final String plate;
    private final VehicleType type;
    private final boolean electric;

    public Vehicle(String plate, VehicleType type, boolean electric) {
        this.plate = Objects.requireNonNull(plate);
        this.type = Objects.requireNonNull(type);
        this.electric = electric;
    }

    public String getPlate() { return plate; }
    public VehicleType getType() { return type; }
    public boolean isElectric() { return electric; }
}