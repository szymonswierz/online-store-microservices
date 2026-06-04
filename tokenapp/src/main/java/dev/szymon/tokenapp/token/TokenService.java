package dev.szymon.tokenapp.token;

import dev.szymon.tokenapp.exception.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public List<TokenEntity> getAllTokens() {
        return tokenRepository.findAll();
    }

    public TokenEntity getTokenEntityById(int id) {
        return tokenRepository.findById(id).orElseThrow(
                () -> new TokenNotFoundException("Token with this id does not exist"));
    }

    public TokenEntity createOrUpdateToken(int userId) {
        TokenEntity tokenEntity = tokenRepository.findById(userId).orElse(null);
        if (tokenEntity != null) {
            tokenEntity.setUuid(UUID.randomUUID());
        } else {
            tokenEntity = new TokenEntity(userId, UUID.randomUUID());
        }
        return tokenRepository.save(tokenEntity);
    }
}
