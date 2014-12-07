package admission.controller;

import admission.model.User;
import admission.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserRegisterController {

    @Autowired
    private UserRegisterService userService;

    //@RequestMapping("register/users/show")
    @RequestMapping("show")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "register/addingUser";
    }

    @RequestMapping("start")
    public String welcomeUser() {
        return "register/start";
    }

    @RequestMapping("register")
    public String registerUser(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        userService.addUser(name);
        model.addAttribute("userList", userService.getAllUsers());

        List<User> samples = userService.getAllUsers();
        for (User u : samples) {
            System.out.println(u.getUserType().japaneseTypeName());
        }

        return "register/allUsers";
    }
//    @RequestMapping(value = "{id}", method = RequestMethod.GET)
//    User getUser(@PathVariable("id") Integer id) {
//        return userService.findById(id);
//    }

    @RequestMapping("/")
    public String home(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "register/addingUser";
    }
}
