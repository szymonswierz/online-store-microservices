package dev.szymon.tokenapp.token;

import dev.szymon.tokenapp.request.UserIdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenRestController {

    private final TokenService tokenService;

    @GetMapping("/")
    public ResponseEntity<List<TokenEntity>> getAllTokens() {
        List<TokenEntity> tokenEntities = tokenService.getAllTokens();
        return new ResponseEntity<>(tokenEntities, HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<Void> generateToken(@RequestBody UserIdRequest userIdRequest) {

        TokenEntity tokenEntity = tokenService.createOrUpdateToken(userIdRequest.getUserId());

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("UUID-Token", tokenEntity.getUuid().toString());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("UUID-Token") String uuidToken,
                                                @RequestBody UserIdRequest userIdRequest) {

        TokenEntity tokenEntity = tokenService.getTokenEntityById(userIdRequest.getUserId());

        if (tokenEntity.getUuid() == null) {
            return new ResponseEntity<>("Token UUID is null", HttpStatus.UNAUTHORIZED);
        }

        UUID uuidFromHeader = UUID.fromString(uuidToken);

        if (Objects.equals(tokenEntity.getUuid(), uuidFromHeader)) {
            return new ResponseEntity<>("Token is valid", HttpStatus.OK);
        }

        return new ResponseEntity<>("Token is invalid", HttpStatus.UNAUTHORIZED);
    }
}
