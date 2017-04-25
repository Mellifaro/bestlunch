package ua.bestlunch.service;

import ua.bestlunch.model.User;
import ua.bestlunch.model.to.UserTo;
import ua.bestlunch.util.exception.NotFoundException;

import java.util.List;


public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void update(UserTo userTo);

    List<User> getAll();

    void evictCache();

    void enable(int id, boolean enabled);
}
