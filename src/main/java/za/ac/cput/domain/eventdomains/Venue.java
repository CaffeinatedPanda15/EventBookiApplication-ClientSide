package za.ac.cput.domain.eventdomains;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Venue {

    @Id
    protected int venueId;
    protected String venueName;
    protected String venueDescription;
    protected String venueAddress;
    protected int venueCapacity;
    protected double venuePrice;
    @Lob
    protected byte[] venueImage;

    protected Venue(Builder builder) {
        this.venueId = builder.venueId;
        this.venueName = builder.venueName;
        this.venueDescription = builder.venueDescription;
        this.venueAddress = builder.venueAddress;
        this.venueCapacity = builder.venueCapacity;
        this.venuePrice =  builder.venuePrice;
        this.venueImage = builder.venueImage;
    }

    public Venue() {

    }

    public int getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueDescription() {
        return venueDescription;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public int getVenueCapacity() {
        return venueCapacity;
    }

    public double getVenuePrice() {
        return venuePrice;
    }

    public byte[] getVenueImage() {
        return venueImage;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "venueId=" + venueId +
                ", venueName='" + venueName + '\'' +
                ", venueDescription='" + venueDescription + '\'' +
                ", venueAddress='" + venueAddress + '\'' +
                ", venueCapacity=" + venueCapacity +
                ", venuePrice=" + venuePrice +
                ", venueImage=" + (venueImage != null ? venueImage.length + " bytes" : "null") +
                '}';
    }

    public static class Builder {
        private int venueId;
        private String venueName;
        private String venueDescription;
        private String venueAddress;
        private int venueCapacity;
        private double venuePrice;
        private byte[] venueImage;

        public Builder setVenueId(int venueId) {
            this.venueId = venueId;
            return this;
        }

        public Builder setVenueName(String venueName) {
            this.venueName = venueName;
            return this;
        }

        public Builder setVenueDescription(String venueDescription) {
            this.venueDescription = venueDescription;
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

        public Builder setVenuePrice(double venuePrice) {
            this.venuePrice = venuePrice;
            return this;
        }

        public Builder setVenueImage(byte[] venueImage) {
            this.venueImage = venueImage;
            return this;
        }

        public Builder copy(Venue venue) {
            this.venueId = venue.venueId;
            this.venueName = venue.venueName;
            this.venueDescription = venue.venueDescription;
            this.venueAddress = venue.venueAddress;
            this.venueCapacity = venue.venueCapacity;
            this.venuePrice = venue.venuePrice;
            this.venueImage = venue.venueImage;
            return this;
        }

        public Venue build() {
            return new Venue(this);
        }
    }



}//end of class
