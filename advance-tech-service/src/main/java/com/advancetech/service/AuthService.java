package com.advancetech.service;

import com.advancetech.domain.entity.User;
import com.advancetech.domain.enums.UserRole;
import com.advancetech.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public User registerUser(String username, String email, String password, String phone) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        
        // Create new user
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.addRole(UserRole.USER);
        user.setEnabled(true);
        
        return userRepository.save(user);
    }
    
    public Authentication authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
    
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return userRepository.findByUsername(username);
        }
        return Optional.empty();
    }
    
    public boolean isCurrentUserAdmin() {
        return getCurrentUser()
            .map(user -> user.hasRole(UserRole.ADMIN))
            .orElse(false);
    }
    
    public boolean isCurrentUserModerator() {
        return getCurrentUser()
            .map(user -> user.isModerator())
            .orElse(false);
    }
}
