package za.ac.cput.domain.eventdomains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.util.Arrays;

@Entity
public class Catering {
    @Id
    protected int cateringId;
    protected String cateringName;
    protected String cateringType;
    protected String cateringDescription;
    protected double cateringPrice;
    protected String cateringContact;
    protected String cateringEmail;
    @Lob
    protected byte[] cateringImage;

    protected Catering(Builder builder) {
        this.cateringId = builder.cateringId;
        this.cateringName = builder.cateringName;
        this.cateringType = builder.cateringType;
        this.cateringDescription = builder.cateringDescription;
        this.cateringPrice = builder.cateringPrice;
        this.cateringContact = builder.cateringContact;
        this.cateringEmail = builder.cateringContact;
        this.cateringImage = builder.cateringImage;
    }

    protected Catering() {

    }

    public int getCateringId() {
        return cateringId;
    }

    public String getCateringName() {
        return cateringName;
    }

    public String getCateringType() {
        return cateringType;
    }

    public String getCateringDescription() {
        return cateringDescription;
    }

    public double getCateringPrice() {
        return cateringPrice;
    }

    public String getCateringContact() {
        return cateringContact;
    }

    public String getCateringEmail() {
        return cateringEmail;
    }

    public byte[] getCateringImage() {
        return cateringImage;
    }

    @Override
    public String toString() {
        return "Cataring{" +
                "id=" + cateringId +
                ", cateringName='" + cateringName + '\'' +
                ", cateringType='" + cateringType + '\'' +
                ", cateringDescription='" + cateringDescription + '\'' +
                ", cateringPrice=" + cateringPrice +
                ", cateringContact='" + cateringContact + '\'' +
                ", cateringEmail='" + cateringEmail + '\'' +
                ", cateringImage=" + Arrays.toString(cateringImage) +
                '}';
    }

    public static class Builder {
        private int cateringId;
        private String cateringName;
        private String cateringType;
        private String cateringDescription;
        private double cateringPrice;
        private String cateringContact;
        private String cateringEmail;
        private byte[] cateringImage;

        public Builder setCateringId(int cateringId) {
            this.cateringId = cateringId;
            return this;
        }

        public Builder setCateringName(String cateringName) {
            this.cateringName = cateringName;
            return this;
        }

        public Builder setCateringType(String cateringType) {
            this.cateringType = cateringType;
            return this;
        }

        public Builder setCateringDescription(String cateringDescription) {
            this.cateringDescription = cateringDescription;
            return this;
        }

        public Builder setCateringPrice(double cateringPrice) {
            this.cateringPrice = cateringPrice;
            return this;
        }

        public Builder setCateringContact(String cateringContact) {
            this.cateringContact = cateringContact;
            return this;
        }

        public Builder setCateringEmail(String cateringEmail) {
            this.cateringEmail = cateringEmail;
            return this;
        }

        public Builder setCateringImage(byte[] cateringImage) {
            this.cateringImage = cateringImage;
            return this;
        }

        public Builder copy(Catering cataring) {
            this.cateringId = cataring.cateringId;
            this.cateringName = cataring.cateringName;
            this.cateringType = cataring.cateringType;
            this.cateringDescription = cataring.cateringDescription;
            this.cateringPrice = cataring.cateringPrice;
            this.cateringContact = cataring.cateringContact;
            this.cateringEmail = cataring.cateringEmail;
            this.cateringImage = cataring.cateringImage;
            return this;
        }


        public Catering build() {
            return new Catering(this);
        }
    }
}
