package com.tobi.notificationService.kafkaConfig;

import com.tobi.notificationService.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "NotificationQuantity", groupId = "alert", containerFactory = "factory")
    void quantityAlert(Product product){
        log.info("The quantity for product " +
                " is getting low. Please look into more restocking options"+
                " The quantity of "+ product.getName() +" is: "+ product.getQuantity());


    }
}
