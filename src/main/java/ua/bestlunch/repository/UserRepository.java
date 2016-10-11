package ua.bestlunch.repository;

import ua.bestlunch.model.User;

import java.util.List;

/**
 * Created by Виктор on 13.09.2016.
 */
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
