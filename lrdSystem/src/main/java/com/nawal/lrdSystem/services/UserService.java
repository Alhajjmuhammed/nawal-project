package com.nawal.lrdSystem.services;

import com.nawal.lrdSystem.model.UserEntity;
import com.nawal.lrdSystem.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<UserEntity> findByID(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

}
