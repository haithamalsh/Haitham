package com.advancetech.service;

import com.advancetech.domain.entity.User;
import com.advancetech.domain.enums.UserRole;
import com.advancetech.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.addRole(UserRole.USER);
        }
        return userRepository.save(user);
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User updateUser(Long userId, User userDetails) {
        User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (userDetails.getPhone() != null) {
            existingUser.setPhone(userDetails.getPhone());
        }
        if (userDetails.getAvatarUrl() != null) {
            existingUser.setAvatarUrl(userDetails.getAvatarUrl());
        }
        
        return userRepository.save(existingUser);
    }
    
    public void updatePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
    
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
