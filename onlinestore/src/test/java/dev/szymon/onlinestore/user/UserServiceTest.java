package dev.szymon.onlinestore.user;

import dev.szymon.onlinestore.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldRegisterUser() {

        int id = 1;
        String username = "jan123";
        String password = "jan123";
        String encodedPassword = "encodedjan123";
        String email = "jan123@gmail.com";

        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        UserEntity user1 = new UserEntity();
        user1.setId(id);
        user1.setUsername(username);
        user1.setPassword(encodedPassword);
        user1.setEmail(email);

        when(userRepository.save(any(UserEntity.class))).thenReturn(user1);

        UserEntity result = userService.registerUser(username, password, email);

        assertEquals("jan123", result.getUsername());
        assertEquals("encodedjan123", result.getPassword());
        assertEquals("jan123@gmail.com", result.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {

        String username = "jan123";
        String password = "jan123";
        String email = "jan123@gmail.com";

        when(userRepository.existsByUsername(username)).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(username, password, email));
    }

}
