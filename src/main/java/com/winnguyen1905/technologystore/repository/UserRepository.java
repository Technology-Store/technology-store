package com.winnguyen1905.technologystore.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnguyen1905.technologystore.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserByUsername(String username);
    Optional<UserEntity> findByUsernameAndRefreshToken(String username, String refreshToken);
}