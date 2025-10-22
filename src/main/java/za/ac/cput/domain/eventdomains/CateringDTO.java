package za.ac.cput.domain.eventdomains;

import jakarta.persistence.Lob;

public class CateringDTO {

    private long cateringId;
    private String cateringName;
    private String cateringType;
    private String cateringDescription;
    private double cateringPrice;
    private String cateringContact;
    private byte[] cateringImage;

    public CateringDTO(long cateringId, String cateringName, String cateringType, String cateringDescription,
                       double cateringPrice, String cateringContact, byte[] cateringImage) {
        this.cateringId = cateringId;
        this.cateringName = cateringName;
        this.cateringType = cateringType;
        this.cateringDescription = cateringDescription;
        this.cateringPrice = cateringPrice;
        this.cateringContact = cateringContact;
        this.cateringImage = cateringImage;
    }

    // Getters and Setters

    public long getCateringId() {
        return cateringId;
    }

    public void setCateringId(int cateringId) {
        this.cateringId = cateringId;
    }

    public String getCateringName() {
        return cateringName;
    }

    public void setCateringName(String cateringName) {
        this.cateringName = cateringName;
    }

    public String getCateringType() {
        return cateringType;
    }

    public void setCateringType(String cateringType) {
        this.cateringType = cateringType;
    }

    public String getCateringDescription() {
        return cateringDescription;
    }

    public void setCateringDescription(String cateringDescription) {
        this.cateringDescription = cateringDescription;
    }

    public double getCateringPrice() {
        return cateringPrice;
    }

    public void setCateringPrice(double cateringPrice) {
        this.cateringPrice = cateringPrice;
    }

    public String getCateringContact() {
        return cateringContact;
    }

    public void setCateringContact(String cateringContact) {
        this.cateringContact = cateringContact;
    }

    public byte[] getCateringImage() {
        return cateringImage;
    }

    public void setCateringImage(byte[] cateringImage) {
        this.cateringImage = cateringImage;
    }
}
