package dev.szymon.bankapp.token;

import dev.szymon.bankapp.request.UserIdRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "tokenapp", url = "http://localhost:8082")
public interface TokenFeignClient {

    @PostMapping("/token/validate")
    ResponseEntity<String> validateToken(@RequestHeader("UUID-Token") String uuidToken,
                                         @RequestBody UserIdRequest userIdRequest);
}
