public class NearestFirstAssignment implements SlotAssignmentStrategy {
    @Override
    public Slot findSlot(ParkingLot lot, Vehicle v) {
        for (Floor f : lot.getFloors()) {
            for (Slot s : f.getSlots()) {
                if (s.isFreeFor(v)) return s;
            }
        }
        return null;
    }
}