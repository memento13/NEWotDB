package pknu.otDB.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(IllegalStateException.class)
    public String errorPageIllegalStateException(IllegalStateException exception, Model model){
        model.addAttribute("msg",exception.getMessage());
        return "error";
    }
}
