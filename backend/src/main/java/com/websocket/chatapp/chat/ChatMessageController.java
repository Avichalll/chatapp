package com.websocket.chatapp.chat;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ChatMessageService chatMessageService;

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    @MessageMapping("/chat")
    public void procesMessage(@Payload ChatMessage chatMessage) {
        // ChatMessage savedMsg = chatMessageService.save(chatMessage);

        Message<ChatMessage> message = MessageBuilder.withPayload(chatMessage)
                .setHeader(KafkaHeaders.TOPIC, "chat-app").build();
        kafkaTemplate.send(message);

        // messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),
        // "/queue/messages",
        // ChatNotification.builder()
        // .id(savedMsg.getId())
        // .senderId(savedMsg.getSenderId())
        // .recipientId(savedMsg.getRecipientId())
        // .content(savedMsg.getContent())
        // .build());
    }

    @GetMapping("/messasge/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessage(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId

    ) {

        return ResponseEntity.ok(chatMessageService.findChatMessage(senderId, recipientId));
    }

    @KafkaListener(topics = "chat-app", groupId = "myGroup")
    public void consumeMessage(ChatMessage chatMessage) {
        ChatMessage savedMsg = chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages",
                ChatNotification.builder()
                        .id(savedMsg.getId())
                        .senderId(savedMsg.getSenderId())
                        .recipientId(savedMsg.getRecipientId())
                        .content(savedMsg.getContent())
                        .build());

    }
}
