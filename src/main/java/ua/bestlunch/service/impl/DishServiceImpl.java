package ua.bestlunch.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.bestlunch.model.Dish;
import ua.bestlunch.repository.DishRepository;
import ua.bestlunch.service.DishService;
import ua.bestlunch.util.exception.ExceptionUtil;

import java.util.List;

@Service
public class DishServiceImpl implements DishService{

    @Autowired
    private DishRepository repository;

    @Override
    public Dish get(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.find(id), id);
    }

    @Override
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public Dish update(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Dish> getAllByLunch(int lunchId) {
        return ExceptionUtil.checkNotFoundWithId(repository.findAllByLunch(lunchId), lunchId);
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurantId) {
        return ExceptionUtil.checkNotFoundWithId(repository.findAllByRestaurant(restaurantId), restaurantId);
    }
}
