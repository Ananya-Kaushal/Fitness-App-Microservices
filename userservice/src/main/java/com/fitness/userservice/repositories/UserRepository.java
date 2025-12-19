package com.fitness.userservice.repositories;

import com.fitness.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsByEmail(String email);

    boolean existsById(String userId);

    User findByEmail(String email);

    Boolean existsByKeycloakId(String userId);
}
