package za.ac.cput.domain.eventdomains;

public class Venue {
    protected int venueId;
    protected String venueName;
    protected String venueAddress;
    protected int venueCapacity;

    private Venue () {
    }

    private Venue(Builder builder) {
        this.venueId = builder.venueId;
        this.venueName = builder.venueName;
        this.venueAddress = builder.venueAddress;
        this.venueCapacity = builder.venueCapacity;
    }

    public int getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public int getVenueCapacity() {
        return venueCapacity;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "venueId=" + venueId +
                ", venueName='" + venueName + '\'' +
                ", venueAddress='" + venueAddress + '\'' +
                ", venueCapacity=" + venueCapacity +
                '}';
    }

    public static class Builder {
        private int venueId;
        private String venueName;
        private String venueAddress;
        private int venueCapacity;

        public Builder setVenueId(int venueId) {
            this.venueId = venueId;
            return this;
        }

        public Builder setVenueName(String venueName) {
            this.venueName = venueName;
            return this;
        }

        public Builder setVenueAddress(String venueAddress) {
            this.venueAddress = venueAddress;
            return this;
        }

        public Builder setVenueCapacity(int venueCapacity) {
            this.venueCapacity = venueCapacity;
            return this;
        }

        public Venue build() {
            return new Venue(this);
        }


    }
    public Venue copy(Venue venue){
        this.venueId = venue.venueId;
        this.venueName = venue.venueName;
        this.venueAddress = venue.venueAddress;
        this.venueCapacity = venue.venueCapacity;
        return this;
    }//end of builer class
}//end of class
