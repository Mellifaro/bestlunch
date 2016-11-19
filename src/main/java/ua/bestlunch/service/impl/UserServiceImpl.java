package ua.bestlunch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ua.bestlunch.model.User;
import ua.bestlunch.repository.UserRepository;
import ua.bestlunch.service.UserService;
import ua.bestlunch.util.exception.ExceptionUtil;
import ua.bestlunch.util.exception.NotFoundException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id){
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id){
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return repository.getByEmail(email);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        repository.save(user);
    }

    @Override
    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void evictCache() {

    }
}
