package com.websocket.chatapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;

import com.websocket.chatapp.chat.ChatMessage;

@Configuration

public class KafakTopicConfig {

    public NewTopic createTopic() {
        return TopicBuilder.name("chat-app").build();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChatMessage> batchFactory(
            ConsumerFactory<String, ChatMessage> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, ChatMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true);
        // Set batch size
        factory.getContainerProperties().setPollTimeout(10000);
        return factory;
    }

}
