public interface SlotAssignmentStrategy {
    Slot findSlot(ParkingLot lot, Vehicle v);
}