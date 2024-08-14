package com.sk.ecommerce.payment;


import com.sk.ecommerce.notification.NotificationProducer;
import com.sk.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

private final PaymentRepository repository;
private final PaymentMapper mapper;
private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentsRequest request) {

        var payments=repository.save(mapper.toPayment(request));

       notificationProducer.sendNotification(
               new PaymentNotificationRequest(
                       request.orderReference(),
                       request.amount(),
                       request.paymentMethod(),
                       request.customer().firstName(),
                       request.customer().lastName(),
                       request.customer().email()
               )
       );
        return  payments.getId();

    }
}
