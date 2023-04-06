package com.lazychess.chessgame.applicationuser;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lazychess.chessgame.dto.RegistrationDto;
import com.lazychess.chessgame.dto.RegistrationResponseDto;
import com.lazychess.chessgame.exception.PasswordsDontMatchException;
import com.lazychess.chessgame.exception.UsernameAlreadyExistsException;
import com.lazychess.chessgame.models.Role;

@Service
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public RegistrationResponseDto saveUser(RegistrationDto registrationDto) {
        validateRegistrationDto(registrationDto);
        List<Role> allRoles = List.of(new Role("ROLE_ADMIN"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ApplicationUser user = new ApplicationUser(registrationDto.getUsername(), passwordEncoder.encode(registrationDto.getPassword()), allRoles);
        ApplicationUser savedUser = applicationUserRepository.save(user);
        return new RegistrationResponseDto(savedUser.getUsername());

    }

    private void validateRegistrationDto(RegistrationDto registrationDto) {
        passwordMatchValidator(registrationDto);
        usernameValidator(registrationDto);
    }

    private void usernameValidator(RegistrationDto registrationDto) {
        if(Boolean.TRUE.equals(applicationUserRepository.existsByUsername(registrationDto.getUsername()))) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
    }

    private void passwordMatchValidator(RegistrationDto registrationDto) {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new PasswordsDontMatchException("Passwords don't match");
        }
    }
}
