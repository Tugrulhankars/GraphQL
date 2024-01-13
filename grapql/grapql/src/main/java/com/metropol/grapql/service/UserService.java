package com.metropol.grapql.service;

import com.metropol.grapql.exception.UserNotFoundException;
import com.metropol.grapql.model.User;
import com.metropol.grapql.model.UserRequest;
import com.metropol.grapql.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }


    public User createUser(UserRequest userRequest){
        User user =
                User.builder()
                        .username(userRequest.getUserName())
                        .mail(userRequest.getMail())
                        .role(userRequest.getRole())
                        .build();
        return userRepository.save(user);
    }

    public User updateUser(UserRequest userRequest){
        User existing = getUserById(userRequest.getId());
        existing.setUsername(userRequest.getUserName());
        existing.setMail(userRequest.getMail());
        existing.setRole(userRequest.getRole());
        return userRepository.save(existing);
    }

    public void deleteUser(Long id){
        User existing = getUserById(id);
        userRepository.delete(existing);
    }
}
