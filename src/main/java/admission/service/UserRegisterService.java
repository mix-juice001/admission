package admission.service;

import admission.model.User;
import admission.model.UserType;
import admission.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegisterService {

    @Autowired
    private UserMapper userRepository;

    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public int addUser(String name) {

        int id = userRepository.getAllUsers().size() + 1;
        User user = new User(new Long(id), name, UserType.ADULT);
        userRepository.save(user);
        return id;
    }
}