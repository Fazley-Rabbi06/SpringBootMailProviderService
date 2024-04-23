package com.mailservice.mailservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Fazley Rabbi
 * @since 4-24-2024
 * @version 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder()
@Entity
@Table(name = "users")
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
    private boolean isEnabled;
}
