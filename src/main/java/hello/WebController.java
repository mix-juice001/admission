package hello;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showForm(Person person) {
        return "form";
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    public String checkPersonInfo(@Validated Person person, BindingResult bindingResult) {
        System.out.println(person.getCar().getColor());
//        System.out.println(person.getCar().getModel());
//        System.out.println(person.getCar().getNumberOfTyres());
        bindingResult.hasFieldErrors("aaa");
        bindingResult.getFieldErrors().forEach(fe -> System.out.println("---" + fe.getField()));
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().size());
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getObjectName()));
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getCode()));
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getCodes()[0]));

            return "form";
        }
        return "redirect:/results";
    }

}
