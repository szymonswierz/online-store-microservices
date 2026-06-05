package dev.szymon.onlinestore.exception;

import feign.FeignException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFound(ProductNotFoundException exception,
                                        Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error-page";
    }

    @ExceptionHandler(TokenDownloadFailedException.class)
    public String handleTokenDownloadFailed(TokenDownloadFailedException exception,
                                        Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error-page";
    }

    @ExceptionHandler(TokenGenerateFailedException.class)
    public String handleTokenGenerateFailed(TokenGenerateFailedException exception,
                                        Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error-page";
    }

    @ExceptionHandler(TransactionFailedException.class)
    public String handleTransactionFailed(TransactionFailedException exception,
                                        Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error-page";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleAlreadyExists(UserAlreadyExistsException exception,
                                        Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error-page";
    }

    @ExceptionHandler(FeignException.class)
    public String handleFeignException(FeignException exception,
                                       Model model) {

        model.addAttribute("errorMessage", exception.contentUTF8());
        return "error-page";
    }

    @ExceptionHandler(InvalidUserDataException.class)
    public String handleInvalidUserData(InvalidUserDataException exception,
                                        Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error-page";
    }

}
