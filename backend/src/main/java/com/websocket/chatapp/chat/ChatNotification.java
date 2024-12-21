package com.websocket.chatapp.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ChatNotification {

    private Integer id;
    private String senderId;
    private String recipientId;
    private String content;

}
