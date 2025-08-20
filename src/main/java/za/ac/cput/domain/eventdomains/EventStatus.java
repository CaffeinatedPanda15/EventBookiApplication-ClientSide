package za.ac.cput.domain.eventdomains;

public enum EventStatus {
    Active,
    Cancelled,
    SoldOut,;

    @Override
    public String toString() {
        return name();
    }
}
