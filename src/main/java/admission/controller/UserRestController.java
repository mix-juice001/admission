package admission.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import admission.model.User;
import admission.service.UserRegisterService;

import java.util.List;


@RestController
@RequestMapping("api/users")
public class UserRestController {
    @Autowired
    UserRegisterService userService;

    @RequestMapping(method = RequestMethod.GET)
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /*
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    User insertUser(@Validated @RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    User updateUser(@PathVariable("id") Long id, @Validated @RequestBody User user) {
        user.setId(id);
        return userService.save(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
    */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    User getUser(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }
}
