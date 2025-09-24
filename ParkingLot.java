import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ParkingLot {
    private final List<Floor> floors = new ArrayList<>();
    private final List<Gate> entryGates = new ArrayList<>();
    private final List<Gate> exitGates  = new ArrayList<>();

    private final SlotAssignmentStrategy assignmentStrategy;
    private final PricingStrategy pricingStrategy;

    private final AtomicLong ticketSeq = new AtomicLong(1000);
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();
    private final Map<String, Slot> slotIndex = new HashMap<>();

    public ParkingLot(SlotAssignmentStrategy assign, PricingStrategy price) {
        this.assignmentStrategy = assign;
        this.pricingStrategy = price;
    }

    /* bootstrapping */
    public void addFloor(Floor f) {
        floors.add(f);
        for (Slot s : f.getSlots()) slotIndex.put(s.getId(), s);
    }
    public void addEntryGate(Gate g) { entryGates.add(g); }
    public void addExitGate(Gate g)  { exitGates.add(g); }

    /* core operations */
    public synchronized Ticket park(Vehicle v) {
        Slot s = assignmentStrategy.findSlot(this, v);
        if (s == null) return null;
        s.occupy();
        String tid = "T-" + ticketSeq.getAndIncrement();
        Ticket t = new Ticket(tid, s.getId(), v.getPlate(), Instant.now(), s.getFloor());
        activeTickets.put(tid, t);
        return t;
    }

    public synchronized Receipt unpark(String ticketId) {
        Ticket t = activeTickets.get(ticketId);
        if (t == null || t.getStatus() != TicketStatus.ACTIVE)
            throw new IllegalArgumentException("Invalid or already paid ticket: " + ticketId);

        Instant exit = Instant.now();
        Duration dur = Duration.between(t.getEntryTime(), exit);

        Slot s = slotIndex.get(t.getSlotId());
        if (s == null) throw new IllegalStateException("Slot missing for ticket " + ticketId);

        double amount = pricingStrategy.calculateAmount(dur, s.getFits());
        s.release();
        t.markPaid(exit);
        activeTickets.remove(ticketId);

        return new Receipt(t.getId(), Math.max(1, dur.toMinutes()), amount);
    }

    public boolean isFullFor(Vehicle v) {
        return assignmentStrategy.findSlot(this, v) == null;
    }

    /* getters */
    public List<Floor> getFloors() { return floors; }
    public List<Gate> getEntryGates() { return entryGates; }
    public List<Gate> getExitGates() { return exitGates; }
}