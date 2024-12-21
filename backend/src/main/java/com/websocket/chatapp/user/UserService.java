package com.websocket.chatapp.user;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    public void addUser(User user) {

        try {
            user.setStatus(Status.ONLINE);
            userRepository.save(user);
        } catch (DataAccessException e) {
            // Log the exception and rethrow or handle it accordingly
            System.err.println("Error saving user: " + e.getMessage());
            throw new RuntimeException("Failed to add user", e);
        }

    }

    public void disconnect(User user) {

        User user1 = userRepository.findById(user.getNickName())
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        user1.setStatus(Status.OFFLINE);

        userRepository.save(user1);

    }

    public List<User> findConnectedUser() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }

}
