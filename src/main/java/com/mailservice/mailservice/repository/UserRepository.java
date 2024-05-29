package com.mailservice.mailservice.repository;

import com.mailservice.mailservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fazley Rabbi
 * @version 1.0
 * @since 5/7/2024
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);

    Boolean existsByEmail(String email);
}
