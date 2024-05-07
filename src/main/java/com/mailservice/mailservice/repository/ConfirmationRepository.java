package com.mailservice.mailservice.repository;

import com.mailservice.mailservice.domain.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fazley Rabbi
 * @version 1.0
 * @since 5/7/2024
 */

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    Confirmation findByToken(String token);
}
