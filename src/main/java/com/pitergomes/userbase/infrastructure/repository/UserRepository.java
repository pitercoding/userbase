package com.pitergomes.userbase.infrastructure.repository;

import com.pitergomes.userbase.infrastructure.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
