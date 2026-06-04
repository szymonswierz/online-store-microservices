package dev.szymon.onlinestore.token;

import dev.szymon.onlinestore.request.UserIdRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tokenapp", url = "http://localhost:8082")
public interface TokenFeignClient {
    @PostMapping("/token/generate")
    ResponseEntity<Void> generateUUIDToken(@RequestBody UserIdRequest userIdRequest);
}