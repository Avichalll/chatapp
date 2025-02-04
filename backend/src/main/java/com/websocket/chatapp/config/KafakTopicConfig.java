package com.websocket.chatapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration

public class KafakTopicConfig {

    public NewTopic createTopic() {
        return TopicBuilder.name("chat-app").build();
    }

}
