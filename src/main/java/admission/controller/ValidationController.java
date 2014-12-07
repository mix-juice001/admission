package admission.controller;


import admission.service.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ValidationController {

    @ModelAttribute
    public UserForm setupForm() {
        return new UserForm();
    }

    @RequestMapping(value = "valid/show", method = RequestMethod.POST)
    public String getAllUsers(@Validated UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {
            return "valid/start";//"valid/validError";
        }
        return "register/addingUser";
    }

    @RequestMapping("valid/start")
    public String welcomeUser() {
        return "valid/start";
    }

}
