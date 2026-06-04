package dev.szymon.tokenapp.token;

import dev.szymon.tokenapp.exception.TokenNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    TokenService tokenService;

    @Test
    void shouldReturnAllTokens() {

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        TokenEntity token1 = new TokenEntity(1, uuid1);

        TokenEntity token2 = new TokenEntity(2, uuid2);

        when(tokenRepository.findAll()).thenReturn(List.of(token1, token2));

        List<TokenEntity> result = tokenService.getAllTokens();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(uuid1, result.get(0).getUuid());
    }

    @Test
    void shouldReturnTokenEntityById() {

        UUID uuid1 = UUID.randomUUID();
        TokenEntity token1 = new TokenEntity(1, uuid1);

        when(tokenRepository.findById(1)).thenReturn(Optional.of(token1));

        TokenEntity result = tokenService.getTokenEntityById(1);

        assertEquals(1, result.getId());
        assertEquals(uuid1, result.getUuid());

    }

    @Test
    void shouldThrowExceptionTokenNotFound() {

        when(tokenRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TokenNotFoundException.class, () -> tokenService.getTokenEntityById(1));
    }


    @Test
    void shouldCreateTokenWhenDoesNotExist() {

        when(tokenRepository.findById(1)).thenReturn(Optional.empty());
        when(tokenRepository.save(any(TokenEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TokenEntity result = tokenService.createOrUpdateToken(1);

        assertEquals(1, result.getId());
        assertNotNull(result.getUuid());
    }

    @Test
    void shouldUpdateTokenWhenExists() {

        UUID uuid1 = UUID.randomUUID();
        TokenEntity token1 = new TokenEntity(1, uuid1);

        when(tokenRepository.findById(1)).thenReturn(Optional.of(token1));
        when(tokenRepository.save(token1)).thenReturn(token1);

        TokenEntity result = tokenService.createOrUpdateToken(1);

        assertEquals(1, result.getId());
        assertNotNull(result.getUuid());
        assertNotEquals(uuid1, result.getUuid());

    }
}
