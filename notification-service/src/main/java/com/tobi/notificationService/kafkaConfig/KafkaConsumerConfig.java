package com.tobi.notificationService.kafkaConfig;

import com.tobi.notificationService.models.Product;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
   @Value("${spring.kafka.bootstrap-servers}")
    private String websocket;

   @Bean
   public Map<String,Object> consumerConfig(){
       Map<String,Object> consume = new HashMap<>();
       consume.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, websocket);
       //consume.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
       //consume.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
       //consume.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Product.class);

   return consume;
   }


    /*public ConsumerFactory<String,Product> consumerFactory(){
       return new DefaultKafkaConsumerFactory<>(consumerConfig());
   }*/

    @Bean
    public ConsumerFactory<String, Product> consumerFactory() {

        JsonDeserializer<Product> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                this.consumerConfig(),
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,Product>> factory(
            ConsumerFactory<String, Product> consumerFactory
    ){
        ConcurrentKafkaListenerContainerFactory<String,Product> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        return kafkaListenerContainerFactory;
    }


}
