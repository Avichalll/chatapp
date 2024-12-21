package com.websocket.chatapp.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.chatapp.chatRoom.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    @Autowired
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {

        var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(() -> new RuntimeException("Chat room Id not Found"));
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;

    }

    public List<ChatMessage> findChatMessage(String senderId, String recipientId) {
        String chatId = chatRoomService.getChatRoomId(senderId, recipientId, true)
                .orElseThrow(() -> new RuntimeException("Chat room ID not found"));

        List<ChatMessage> allChatMessage = chatMessageRepository.findByChatId(chatId);
        return allChatMessage;

    }

}
