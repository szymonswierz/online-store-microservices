package dev.szymon.onlinestore.bank;

import dev.szymon.onlinestore.exception.TransactionFailedException;
import dev.szymon.onlinestore.response.BankAccountResponse;
import dev.szymon.onlinestore.request.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankFeignClient bankFeignClient;

    public String sendTransactionToBank(String uuidToken, int userId, double transactionAmount) {
        ResponseEntity<String> response = bankFeignClient.transactionOnAccount(uuidToken,
                new TransactionRequest(userId, transactionAmount));
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new TransactionFailedException("Transaction could not be sent");
    }

    public BankAccountResponse getBankAccountById(int id) {

        return bankFeignClient.getBankAccountById(id);
    }
}
