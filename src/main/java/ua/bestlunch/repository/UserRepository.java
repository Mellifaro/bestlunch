package ua.bestlunch.repository;

import ua.bestlunch.model.User;
import ua.bestlunch.util.exception.NotFoundException;

import java.util.List;


public interface UserRepository {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
