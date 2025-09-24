import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final int number;
    private final List<Slot> slots = new ArrayList<>();

    public Floor(int number) { this.number = number; }

    public int getNumber() { return number; }
    public List<Slot> getSlots() { return slots; }
    public void addSlot(Slot s) { slots.add(s); }
}