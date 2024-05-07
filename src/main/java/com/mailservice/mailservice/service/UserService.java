package com.mailservice.mailservice.service;

import com.mailservice.mailservice.domain.User;

/**
 * @author Fazley Rabbi
 * @version 1.0
 * @since 5/7/2024
 */

public interface UserService {
     User saveUser(User user);
     Boolean verifyToken(String token);
}
