package dev.szymon.onlinestore.user;

import dev.szymon.onlinestore.exception.InvalidUserDataException;
import dev.szymon.onlinestore.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity registerUser(String username, String password, String email) {

        if (username == null || username.isBlank()) {
            throw new InvalidUserDataException("Username cannot be empty");
        }
        if (password == null || password.isBlank()) {
            throw new InvalidUserDataException("Password cannot be empty");
        }
        if (email == null || email.isBlank()) {
            throw new InvalidUserDataException("Email cannot be empty");
        }

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("User already exists");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setEmail(email);
        return userRepository.save(userEntity);
    }
}
