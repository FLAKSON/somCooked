package org.maksymtiutiunnyk.somcooked.services;

import org.maksymtiutiunnyk.somcooked.dtos.UserRegistrationDto;
import org.maksymtiutiunnyk.somcooked.dtos.UserRegistrationResponseDto;
import org.maksymtiutiunnyk.somcooked.entities.UserEntity;
import org.maksymtiutiunnyk.somcooked.enums.Role;
import org.maksymtiutiunnyk.somcooked.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserRegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserRegistrationResponseDto register(UserRegistrationDto RegistrationDto) {
        if (userRepository.findByUsername(RegistrationDto.username()).isPresent()) {
            throw new RuntimeException("Username is already in use");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(RegistrationDto.username());
        userEntity.setPassword(passwordEncoder.encode(RegistrationDto.password()));
        userEntity.setEmail(RegistrationDto.username());
        userEntity.setRole(Role.USER);
        userRepository.save(userEntity);
        return new UserRegistrationResponseDto(userEntity.getId(), userEntity.getUsername(), userEntity.getRole().name());
    }
}
