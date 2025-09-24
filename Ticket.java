import java.time.Instant;
public class Ticket {
    private final String id;
    private final String slotId;
    private final String vehiclePlate;
    private final Instant entryTime;
    private final int floor;

    private TicketStatus status = TicketStatus.ACTIVE;
    private Instant exitTime;

    public Ticket(String id, String slotId, String vehiclePlate, Instant entryTime, int floor) {
        this.id = id;
        this.slotId = slotId;
        this.vehiclePlate = vehiclePlate;
        this.entryTime = entryTime;
        this.floor = floor;
    }

    public String getId() { return id; }
    public String getSlotId() { return slotId; }
    public String getVehiclePlate() { return vehiclePlate; }
    public Instant getEntryTime() { return entryTime; }
    public int getFloor() { return floor; }
    public TicketStatus getStatus() { return status; }
    public Instant getExitTime() { return exitTime; }

    public void markPaid(Instant exitTime) {
        this.exitTime = exitTime;
        this.status = TicketStatus.PAID;
    }
}