package com.sk.ecommerce.kafka.payment;

import com.sk.ecommerce.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
