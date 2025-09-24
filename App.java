public class App {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot lot = buildSampleLot();

        Gate e1 = new Gate(1, GateType.ENTRY, lot); lot.addEntryGate(e1);
        Gate e2 = new Gate(2, GateType.ENTRY, lot); lot.addEntryGate(e2);
        Gate x1 = new Gate(3, GateType.EXIT,  lot); lot.addExitGate(x1);

        Vehicle c1 = new Vehicle("DL1AA0001", VehicleType.CAR, false);
        Vehicle ev = new Vehicle("DL1EV0420", VehicleType.CAR, true);
        Vehicle bk = new Vehicle("DL5BK7777", VehicleType.BIKE, false);
        Vehicle bs = new Vehicle("DL9BUS999", VehicleType.BUS, false);

        Ticket t1 = e1.enter(c1);
        Ticket t2 = e2.enter(ev);
        Ticket t3 = e2.enter(bk);
        Ticket t4 = e1.enter(bs);

        Thread.sleep(500); // simulate stay

        if (t1 != null) System.out.println(x1.exit(t1.getId()));
        if (t2 != null) System.out.println(x1.exit(t2.getId()));
        if (t3 != null) System.out.println(x1.exit(t3.getId()));
        if (t4 != null) System.out.println(x1.exit(t4.getId()));
    }

    private static ParkingLot buildSampleLot() {
        ParkingLot lot = new ParkingLot(new NearestFirstAssignment(), new SimplePricing());

        // floors 0..2 with mixed slots
        for (int f = 0; f <= 2; f++) {
            Floor floor = new Floor(f);
            for (int i = 1; i <= 4; i++)
                floor.addSlot(new Slot("F" + f + "-B-" + i, f, VehicleType.BIKE, i == 1));
            for (int i = 1; i <= 8; i++)
                floor.addSlot(new Slot("F" + f + "-C-" + i, f, VehicleType.CAR, i <= 2));   // first two are EV
            for (int i = 1; i <= 2; i++)
                floor.addSlot(new Slot("F" + f + "-U-" + i, f, VehicleType.BUS, false));
            lot.addFloor(floor);
        }
        return lot;
    }
}