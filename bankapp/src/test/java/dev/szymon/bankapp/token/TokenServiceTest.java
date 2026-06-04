package dev.szymon.bankapp.token;

import dev.szymon.bankapp.request.UserIdRequest;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @Mock
    private TokenFeignClient tokenFeignClient;

    @InjectMocks
    private TokenService tokenService;

    @Test
    void shouldReturnTrueWhenValidateToken() {

        ResponseEntity<String> response = new ResponseEntity<>("Token is valid", HttpStatus.OK);

        when(tokenFeignClient.validateToken(any(String.class), any(UserIdRequest.class))).thenReturn(response);

        boolean result = tokenService.validateToken("f6813644-1b8c-4574-8776-9d2bb3c1383c", 1);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenFeignExceptionIsThrownOnValidateToken() {

        when(tokenFeignClient.validateToken(any(String.class), any(UserIdRequest.class))).thenThrow(FeignException.class);

        boolean result = tokenService.validateToken("invalid-token", 1);

        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenValidateToken() {

        ResponseEntity<String> response = new ResponseEntity<>("Token is invalid", HttpStatus.UNAUTHORIZED);

        when(tokenFeignClient.validateToken(any(String.class), any(UserIdRequest.class))).thenReturn(response);

        boolean result = tokenService.validateToken("invalid-token", 1);

        assertFalse(result);
    }
}
