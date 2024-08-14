package com.sk.ecommerce.kafka;

import com.sk.ecommerce.email.EmailService;
import com.sk.ecommerce.email.EmailTempletes;
import com.sk.ecommerce.kafka.order.OrderConfirmation;
import com.sk.ecommerce.kafka.payment.NotificationRepository;
import com.sk.ecommerce.kafka.payment.PaymentConfirmation;
import com.sk.ecommerce.notification.Notification;
import com.sk.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.sk.ecommerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.sk.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
     private final EmailService emailService;

@KafkaListener(topics = "payment-topic")
    public void consumePaymentSucessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("consume the message from payment topic  Topic :: %S ",paymentConfirmation));
        System.out.println("consume the message from payment topic  Topic :: %S " +paymentConfirmation);
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()

        );
        //to do send email here

    var customerName=paymentConfirmation.customerFirstName()+" "+paymentConfirmation.customerLastName();
    emailService.sentPaymentSucessEmail(
            paymentConfirmation.customerEmail(),
            customerName,
            paymentConfirmation.amount(),
            paymentConfirmation.orderReference()
    );

    }
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("consume the message from payment topic  Topic :: %S ",orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()

        );
        //to do send email here
        var customerName=orderConfirmation.customer().firstName()+" "+orderConfirmation.customer().lastName();
        emailService.sentOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

}
