package admission.repository;

import admission.model.User;

import java.util.List;

public interface UserMapper {

    public List<User> getAllUsers();

    public User findById(Integer id);

    public abstract void save(User user);
}
