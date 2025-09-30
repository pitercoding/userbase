package com.pitergomes.userbase.infrastructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 100, nullable = false)
    @NotBlank(message = "Full name is required!")
    private String fullName;

    @Column(name = "email", unique = true, length = 50, nullable = false)
    @NotBlank(message = "Email is required!")
    @Email(message = "Email should be valid!")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone_number", length = 20)
    @Pattern(regexp = "\\+?[0-9]*", message = "Phone number must contain only digits and optional '+'")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is required!")
    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
