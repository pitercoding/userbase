package com.pitergomes.userbase.controller;

import com.pitergomes.userbase.business.UserService;
import com.pitergomes.userbase.infrastructure.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        user.setCreatedAt(LocalDateTime.now());
        User saved = userService.saveUser(user);

        return ResponseEntity
                .created(URI.create("/user?id=" + saved.getId())) // Location header
                .body(saved); // retorna o usuário criado
    }

    @GetMapping
    public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserByEmail(@RequestParam String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build(); // boa prática: 204 No Content
    }

    @PutMapping
    public ResponseEntity<User> updateUserById(@RequestParam Long id, @Valid @RequestBody User user) {
        User updated = userService.updateUserById(id, user);
        return ResponseEntity.ok(updated); // retorna usuário atualizado
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUserById(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        User updated = userService.patchUserById(id, updates);
        return ResponseEntity.ok(updated);
    }
}