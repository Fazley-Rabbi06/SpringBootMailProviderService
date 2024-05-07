package com.mailservice.mailservice.service.impl;

import com.mailservice.mailservice.domain.Confirmation;
import com.mailservice.mailservice.domain.User;
import com.mailservice.mailservice.repository.ConfirmationRepository;
import com.mailservice.mailservice.repository.UserRepository;
import com.mailservice.mailservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;

    @Transactional
    @Override
    public User saveUser(User user) {
        if (userRepository.existByEmail(user.getEmail())) {
            log.error("User already exist!!");
            throw new RuntimeException("User already exist!!");
        }
        user.setEnabled(false);
        User newUser = userRepository.save(user);
        Confirmation confirmation = new Confirmation(newUser);
        confirmationRepository.save(confirmation);
        /* Mail sender code */

        log.info("Saved user: " + newUser);
        return newUser;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation;
        try {
            confirmation = confirmationRepository.findByToken(token);
            if (confirmation == null) return Boolean.FALSE;
            User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
            user.setEnabled(Boolean.TRUE);
            userRepository.save(user);
        } catch (Exception ex) {
            log.error("Exception : " + ex.getMessage());
        }
        return Boolean.TRUE;
    }
}
