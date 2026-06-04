package dev.szymon.bankapp.token;

import dev.szymon.bankapp.request.UserIdRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenFeignClient tokenFeignClient;

    public boolean validateToken(String uuidToken, int userId) {

        try {

            ResponseEntity<String> response = tokenFeignClient.validateToken(uuidToken,
                    new UserIdRequest(userId));

            return response != null && response.getStatusCode().is2xxSuccessful();

        } catch (FeignException e) {

            return false;
        }

    }

}
