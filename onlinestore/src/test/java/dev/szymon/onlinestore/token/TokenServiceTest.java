package dev.szymon.onlinestore.token;

import dev.szymon.onlinestore.exception.TokenDownloadFailedException;
import dev.szymon.onlinestore.exception.TokenGenerateFailedException;
import dev.szymon.onlinestore.request.UserIdRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @Mock
    private TokenFeignClient tokenFeignClient;

    @InjectMocks
    private TokenService tokenService;

    @Test
    void shouldReturnProcessToken() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("UUID-Token", "f6813644-1b8c-4574-8776-9d2bb3c1383c");
        ResponseEntity<Void> response = new ResponseEntity<>(httpHeaders, HttpStatus.OK);

        when(tokenFeignClient.generateUUIDToken(any(UserIdRequest.class))).thenReturn(response);

        String result = tokenService.processToken(1);

        assertEquals("f6813644-1b8c-4574-8776-9d2bb3c1383c", result);
    }

    @Test
    void shouldThrowExceptionTokenDownloadFailed() {

        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        when(tokenFeignClient.generateUUIDToken(any(UserIdRequest.class))).thenReturn(response);

        assertThrows(TokenDownloadFailedException.class, () -> tokenService.processToken(1));
    }

    @Test
    void shouldThrowExceptionTokenGenerateFailed() {

        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);

        when(tokenFeignClient.generateUUIDToken(any(UserIdRequest.class))).thenReturn(response);

        assertThrows(TokenGenerateFailedException.class, () -> tokenService.processToken(1));
    }

}
