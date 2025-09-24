

public class Slot {
    private final String id;       
    private final int floor;
    private final VehicleType fits;  
    private final boolean hasCharger;
    private boolean occupied;

    public Slot(String id, int floor, VehicleType fits, boolean hasCharger) {
        this.id = id;
        this.floor = floor;
        this.fits = fits;
        this.hasCharger = hasCharger;
        this.occupied = false;
    }

    public boolean isFreeFor(Vehicle v) {
        if (occupied) return false;
        if (this.fits != v.getType()) return false;
        if (v.isElectric() && !this.hasCharger) return false;
        return true;
    }

    public void occupy()  { occupied = true; }
    public void release() { occupied = false; }

    public String getId() { return id; }
    public int getFloor() { return floor; }
    public VehicleType getFits() { return fits; }
    public boolean hasCharger() { return hasCharger; }
    public boolean isOccupied() { return occupied; }
}
