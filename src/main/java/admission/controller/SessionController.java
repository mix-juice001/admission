package admission.controller;

import admission.bean.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionController {

    @ModelAttribute
    public UserBean addUser2model() {
        return new UserBean("hoge", 20, "M");
    }

    @RequestMapping("/attr/first")
    public String first() {
        addUser2model();
        return "attr/first";
    }

    @RequestMapping("/attr/second")
    public String second(@ModelAttribute UserBean userBean) {
        System.out.println(userBean.getName());
        return "attr/first";
    }
}
