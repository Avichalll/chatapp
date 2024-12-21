package com.websocket.chatapp.chat;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void procesMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedMsg = chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages",
                ChatNotification.builder()
                        .id(savedMsg.getId())
                        .senderId(savedMsg.getSenderId())
                        .recipientId(savedMsg.getRecipientId())
                        .content(savedMsg.getContent())
                        .build());
    }

    @GetMapping("/messasge/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessage(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId

    ) {

        return ResponseEntity.ok(chatMessageService.findChatMessage(senderId, recipientId));
    }

}
