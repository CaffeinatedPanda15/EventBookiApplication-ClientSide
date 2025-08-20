//Muhammad Siddeeq Rabin
//STUDENT :221084096
//Group kN13

package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import za.ac.cput.util.Helper;

import java.util.Date;



public class PaymentFactory {

    public static Payment createPayment(int paymentID, double amount, Date paymentDate,
                                        String paymentMethod, Payment.Status status) {

        // Validate inputs using Helper
        if (Helper.isZeroOrNull(paymentID) || amount <= 0 ||
            Helper.isNullorEmpty(paymentMethod)) {
            return null;
        }

        // Default to current date if none provided
        if (paymentDate == null) {
            paymentDate = new Date();
        }

        // Default to Pending if status not provided
        if (status == null) {
            status = Payment.Status.Pending;
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
