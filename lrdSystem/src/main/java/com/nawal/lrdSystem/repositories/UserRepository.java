package com.nawal.lrdSystem.repositories;

import com.nawal.lrdSystem.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
