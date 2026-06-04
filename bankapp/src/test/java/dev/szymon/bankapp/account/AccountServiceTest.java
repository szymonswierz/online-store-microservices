package dev.szymon.bankapp.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void shouldReturnAll() {

        AccountEntity account1 = new AccountEntity(1, 200.00);
        AccountEntity account2 = new AccountEntity(2, 300.00);

        when(accountRepository.findAll()).thenReturn(List.of(account1, account2));

        List<AccountEntity> result = accountService.getAllAccounts();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(200.00, result.get(0).getAccountBalance());

    }

    @Test
    void shouldReturnAccountById() {

        AccountEntity account1 = new AccountEntity(1, 200.00);

        when(accountRepository.findById(1)).thenReturn(Optional.of(account1));

        AccountEntity result = accountService.getAccountById(1);

        assertEquals(1, result.getId());
        assertEquals(200.00, result.getAccountBalance());
    }

    @Test
    void shouldReturnNullWhenAccountDoesNotExist() {

        when(accountRepository.findById(1)).thenReturn(Optional.empty());

        AccountEntity result = accountService.getAccountById(1);

        assertNull(result);
    }

    @Test
    void shouldReturnSaveAccount() {

        AccountEntity account1 = new AccountEntity(1, 200.00);

        accountService.saveAccount(account1);

        verify(accountRepository).save(account1);
    }

    @Test
    void shouldReturnCreateAccountWhenAccountDoesNotExist() {

        AccountEntity account1 = new AccountEntity(1, 20000);

        when(accountRepository.findById(1)).thenReturn(Optional.empty());
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(account1);

        AccountEntity result = accountService.getOrCreateAccount(1);

        assertEquals(1, result.getId());
        assertEquals(20000.00, result.getAccountBalance());

    }

    @Test
    void shouldReturnGetAccountWhenAccountDoesExist() {

        AccountEntity account1 = new AccountEntity(1, 200.00);

        when(accountRepository.findById(1)).thenReturn(Optional.of(account1));

        AccountEntity result = accountService.getOrCreateAccount(1);

        assertEquals(1, result.getId());
        assertEquals(200.00, result.getAccountBalance());
    }
}
