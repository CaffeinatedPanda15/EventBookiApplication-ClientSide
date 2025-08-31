//Muhammad Siddeeq Rabin
//STUDENT :221084096
//Group kN13

package za.ac.cput.factory.eventfactories;

import za.ac.cput.domain.Payment;
import za.ac.cput.util.Helper;

import java.util.Date;



public class PaymentFactory {

    public static Payment createPayment(int paymentID, double amount, Date paymentDate,
                                        String paymentMethod, Payment.Status status) {
    if (paymentID <= 0 || amount <= 0 || paymentDate == null || Helper.isNullorEmpty(paymentMethod) || status == null) {
            return null;
        }

        return new Payment.Builder()
                .withPaymentID(paymentID)
                .withAmount(amount)
                .withPaymentDate(paymentDate)
                .withPaymentMethod(paymentMethod)
                .withStatus(status)
                .build();
    }
}
