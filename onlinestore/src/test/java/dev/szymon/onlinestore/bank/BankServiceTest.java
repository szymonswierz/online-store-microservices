package dev.szymon.onlinestore.bank;

import dev.szymon.onlinestore.exception.TransactionFailedException;
import dev.szymon.onlinestore.request.TransactionRequest;
import dev.szymon.onlinestore.response.BankAccountResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

    @Mock
    private BankFeignClient bankFeignClient;

    @InjectMocks
    private BankService bankService;

    @Test
    void shouldReturnSendTransactionToBank() {

        ResponseEntity<String> response = new ResponseEntity<>("Account balance has been changed", HttpStatus.OK);

        when(bankFeignClient.transactionOnAccount(any(String.class), any(TransactionRequest.class))).thenReturn(response);

        String result = bankService.sendTransactionToBank("f6813644-1b8c-4574-8776-9d2bb3c1383c",
                1, 2999.00);

        assertEquals("Account balance has been changed", result);
    }

    @Test
    void shouldThrowExceptionTransactionFailed() {

        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        when(bankFeignClient.transactionOnAccount(any(String.class), any(TransactionRequest.class))).thenReturn(response);

        assertThrows(TransactionFailedException.class, () -> bankService.sendTransactionToBank("f6813644-1b8c-4574-8776-9d2bb3c1383c",
                1, 2999.00));


    }

    @Test
    void shouldReturnBankAccountById() {

        BankAccountResponse bankAccount1 = new BankAccountResponse();
        bankAccount1.setId(1);
        bankAccount1.setAccountBalance(20000.00);

        when(bankFeignClient.getBankAccountById(1)).thenReturn(bankAccount1);

        BankAccountResponse result = bankService.getBankAccountById(1);

        assertEquals(1, result.getId());
        assertEquals(20000.00, result.getAccountBalance());
    }

}
