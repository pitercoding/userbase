package com.pitergomes.userbase.business;

import com.pitergomes.userbase.infrastructure.entities.User;
import com.pitergomes.userbase.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // ---- Create ---- //
    public User saveUser(User user) {
        return repository.saveAndFlush(user);
    }

    // ---- Read ---- //
    public User findUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException("User with email " + email + " not found.")
        );
    }

    // ---- Update ---- //
    public User updateUserById(Long id, User user) {
        User userEntity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found!"));

        if (user.getEmail() != null) userEntity.setEmail(user.getEmail());
        if (user.getFullName() != null) userEntity.setFullName(user.getFullName());
        if (user.getBirthDate() != null) userEntity.setBirthDate(user.getBirthDate());
        if (user.getPhoneNumber() != null) userEntity.setPhoneNumber(user.getPhoneNumber());
        if (user.getPassword() != null) userEntity.setPassword(user.getPassword()); // hash aqui depois

        return repository.save(userEntity);
    }

    // ---- Delete ---- //
    public void deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
    }
}
