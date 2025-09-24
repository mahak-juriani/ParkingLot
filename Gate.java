public class Gate {
    private final int id;
    private final GateType type;
    private final ParkingLot lot;

    public Gate(int id, GateType type, ParkingLot lot) {
        this.id = id;
        this.type = type;
        this.lot = lot;
    }

    public Ticket enter(Vehicle v) {
        if (type != GateType.ENTRY) throw new IllegalStateException("Not an entry gate");
        Ticket t = lot.park(v);
        if (t == null) {
            System.out.println("[Gate " + id + "] Denied entry: Lot is full for " + v.getType() +
                (v.isElectric() ? " (EV)" : ""));
            return null;
        }
        System.out.println("[Gate " + id + "] Ticket " + t.getId() +
            " | floor=" + t.getFloor() + " | slot=" + t.getSlotId() + " | at " + t.getEntryTime());
        return t;
    }

    public Receipt exit(String ticketId) {
        if (type != GateType.EXIT) throw new IllegalStateException("Not an exit gate");
        Receipt r = lot.unpark(ticketId);
        System.out.println("[Gate " + id + "] Payment received for " + ticketId + " => " + r.getAmount());
        return r;
    }

    public int getId() { return id; }
    public GateType getType() { return type; }
}