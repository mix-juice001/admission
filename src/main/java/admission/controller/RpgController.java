package admission.controller;

import admission.model.User;
import admission.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RpgController {

    @Autowired
    private UserRegisterService userService;

    @RequestMapping("/prg/add")
    public String registerUser(@RequestParam(value="name", required=false, defaultValue="World") String name, RedirectAttributes attr) {
        userService.addUser(name);
        attr.addFlashAttribute("message", "登録完了しました。" );

        List<User> samples = userService.getAllUsers();
        for (User u : samples) {
            System.out.println(u.getUserType().japaneseTypeName());
        }

        return "redirect:/prg/to";
    }

    @RequestMapping("/prg/to")
    public String printRegisterResult(Model model) {
        System.out.println("redirect");
        model.addAttribute("userList", userService.getAllUsers());
        return "prg/registerResult";
    }

    @RequestMapping("/prg/start")
    public String startPRG() {

        return "prg/registerUser";
    }
}
