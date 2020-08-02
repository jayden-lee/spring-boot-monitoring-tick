package com.jayden.monitoring.user;

import com.jayden.monitoring.db.entity.User;
import com.jayden.monitoring.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(CreateUserRequest request) {
        User user = request.toEntity();
        userRepository.save(user);
        return user;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not Found User"));
    }
}
