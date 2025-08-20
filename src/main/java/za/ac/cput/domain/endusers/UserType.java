package za.ac.cput.domain.endusers;

public enum UserType {
    ADMIN,
    CUSTOMER, userType;

    @Override
    public String toString() {
        return name();
    }
}
