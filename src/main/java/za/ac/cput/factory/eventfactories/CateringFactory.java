package za.ac.cput.factory.eventfactories;

import za.ac.cput.domain.eventdomains.Catering;

public class CateringFactory {
    public static Catering createCatering(int cateringId, String cateringName, String cateringType, String cateringDescription, double cateringPrice, String cateringContact, String cateringEmail, byte[] cateringImage) {
        return new Catering.Builder()
                .setCateringId(cateringId)
                .setCateringName(cateringName)
                .setCateringType(cateringType)
                .setCateringDescription(cateringDescription)
                .setCateringPrice(cateringPrice)
                .setCateringContact(cateringContact)
                .setCateringEmail(cateringEmail)
                .setCateringImage(cateringImage)
                .build();
    }
}
