package dev.szymon.onlinestore.token;

import dev.szymon.onlinestore.exception.TokenDownloadFailedException;
import dev.szymon.onlinestore.exception.TokenGenerateFailedException;
import dev.szymon.onlinestore.request.UserIdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenFeignClient tokenFeignClient;

    public String processToken(int userId) {

        ResponseEntity<Void> response = tokenFeignClient.generateUUIDToken(new UserIdRequest(userId));

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new TokenDownloadFailedException("Failed to download the token: " + response.getStatusCode());
        }

        String uuid = response.getHeaders().getFirst("UUID-Token");

        if (uuid == null) {
            throw new TokenGenerateFailedException("Token app did not generate token");
        }

        return uuid;
    }
}