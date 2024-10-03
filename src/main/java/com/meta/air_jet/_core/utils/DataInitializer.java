package com.meta.air_jet._core.utils;

import com.meta.air_jet.user.domain.User;
import com.meta.air_jet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .loginId("user")
                .password(passwordEncoder.encode("1234"))
                .nickname("user")
                .build();
        userRepository.save(user);
    }
}
