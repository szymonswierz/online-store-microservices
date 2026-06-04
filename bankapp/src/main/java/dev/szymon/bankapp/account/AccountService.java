package dev.szymon.bankapp.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountEntity getAccountById(int id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void saveAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }

    public AccountEntity getOrCreateAccount(int id) {

        AccountEntity accountEntity = accountRepository.findById(id).orElse(null);

        if (accountEntity == null) {
            return accountRepository.save(new AccountEntity(id, 20000));
        }
        return accountEntity;
    }
}

