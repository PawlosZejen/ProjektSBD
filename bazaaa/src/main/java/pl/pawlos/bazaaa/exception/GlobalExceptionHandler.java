package pl.pawlos.bazaaa.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrity(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("error", "Błąd danych: " + ex.getMostSpecificCause().getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("error", "Wystąpił błąd: " + ex.getMessage());
        return "error";
    }
}
