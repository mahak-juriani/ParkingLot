public class Receipt {
    private final String ticketId;
    private final long parkedMinutes;
    private final double amount;

    public Receipt(String ticketId, long parkedMinutes, double amount) {
        this.ticketId = ticketId;
        this.parkedMinutes = parkedMinutes;
        this.amount = amount;
    }

    public String getTicketId() { return ticketId; }
    public long getParkedMinutes() { return parkedMinutes; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "Receipt{ticket=" + ticketId +
               ", minutes=" + parkedMinutes +
               ", amount=" + amount + "}";
    }
}