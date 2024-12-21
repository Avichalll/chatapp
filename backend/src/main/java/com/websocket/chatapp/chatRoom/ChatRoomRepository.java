package com.websocket.chatapp.chatRoom;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
