package com.jayden.monitoring.db.repository;

import com.jayden.monitoring.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
