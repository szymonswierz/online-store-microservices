package dev.szymon.bankapp.account;

import dev.szymon.bankapp.request.TransactionRequest;
import dev.szymon.bankapp.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountRestController {

    private final TokenService tokenService;
    private final AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<AccountEntity>> getAll() {

        List<AccountEntity> accountEntities = accountService.getAllAccounts();

        return new ResponseEntity<>(accountEntities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountEntity> getAccount(@PathVariable int id) {

        AccountEntity accountEntity = accountService.getAccountById(id);

        if (accountEntity == null) {

            return new ResponseEntity<>(accountEntity, HttpStatus.NOT_FOUND);

        } else {

            return new ResponseEntity<>(accountEntity, HttpStatus.OK);
        }
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> transactionOnAccount(@RequestHeader("UUID-Token") String uuidToken,
                                                       @RequestBody TransactionRequest transactionRequest) {

        if (uuidToken == null) {
            return new ResponseEntity<>("Token is null", HttpStatus.UNAUTHORIZED);
        }

        boolean validToken = tokenService.validateToken(uuidToken, transactionRequest.getUserId());

        if (!validToken) {
            return new ResponseEntity<>("Token is invalid", HttpStatus.UNAUTHORIZED);
        }

        System.out.println("Token has been validated");

        AccountEntity accountEntity = accountService.getOrCreateAccount(transactionRequest.getUserId());

        double balanceAfterTransaction = accountEntity.getAccountBalance() - transactionRequest.getTransactionAmount();

        if (balanceAfterTransaction < 0) {
            return new ResponseEntity<>("Insufficient funds in the account", HttpStatus.BAD_REQUEST);
        }

        accountEntity.setAccountBalance(balanceAfterTransaction);
        accountService.saveAccount(accountEntity);

        return new ResponseEntity<>("Account balance has been changed", HttpStatus.OK);
    }

}
