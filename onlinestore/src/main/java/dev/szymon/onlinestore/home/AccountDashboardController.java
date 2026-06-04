package dev.szymon.onlinestore.home;


import dev.szymon.onlinestore.bank.BankService;
import dev.szymon.onlinestore.response.BankAccountResponse;
import dev.szymon.onlinestore.user.UserEntity;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccountDashboardController {

    private final BankService bankService;

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserEntity userEntity,
                            Model model) {

        model.addAttribute("user", userEntity);

        try {
            BankAccountResponse bankAccountResponse = bankService.getBankAccountById(userEntity.getId());

            model.addAttribute("bankAccount", bankAccountResponse);

            return "dashboard";

        } catch (FeignException.NotFound e) {
            return "empty-dashboard";
        }


    }

}
