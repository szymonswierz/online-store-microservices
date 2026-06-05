package dev.szymon.onlinestore.transaction;

import dev.szymon.onlinestore.bank.BankService;
import dev.szymon.onlinestore.product.ProductEntity;
import dev.szymon.onlinestore.product.ProductService;
import dev.szymon.onlinestore.token.TokenService;
import dev.szymon.onlinestore.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final ProductService productService;
    private final TokenService tokenService;
    private final BankService bankService;

    @PostMapping("/{productId}")
    public String makeNewTransaction(@PathVariable int productId,
                                     @AuthenticationPrincipal UserEntity userEntity,
                                     Model model) {

        ProductEntity productEntity = productService.getProductById(productId);

        model.addAttribute("product", productEntity);

        int userId = userEntity.getId();

        String uuid = tokenService.processToken(userId);

        bankService.sendTransactionToBank(uuid, userId, productEntity.getPrice());

        return "transaction-confirmation";
    }
}
