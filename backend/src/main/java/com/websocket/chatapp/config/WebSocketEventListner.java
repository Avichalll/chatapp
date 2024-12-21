package com.websocket.chatapp.config;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j

public class WebSocketEventListner {

    // private final SimpMessageSendingOperations messageTemplate;

    // @EventListener
    // public void handleWebSocketDisconnectListner(SessionDisconnectEvent event) {

    // StompHeaderAccessor headerAccessor =
    // StompHeaderAccessor.wrap(event.getMessage());
    // String username = (String)
    // headerAccessor.getSessionAttributes().get("username");
    // if (username != null) {
    // log.info("User disconnected : {}", username);
    // ChatMessage chatMessage = ChatMessage.builder()
    // .type(MessageType.LEAVE)
    // .sender(username)
    // .build();
    // messageTemplate.convertAndSend("/topic/public", chatMessage);

    // }

    // // todo

    // }

}
