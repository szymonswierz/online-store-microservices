package dev.szymon.onlinestore.bank;

import dev.szymon.onlinestore.response.BankAccountResponse;
import dev.szymon.onlinestore.request.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "bankapp", url = "http://localhost:8081")
public interface BankFeignClient {
    @PostMapping("/account/transaction")
    ResponseEntity<String> transactionOnAccount(@RequestHeader("UUID-Token") String uuidToken, @RequestBody TransactionRequest transactionRequest);

    @GetMapping("/account/{id}")
    BankAccountResponse getBankAccountById(@PathVariable int id);
}
