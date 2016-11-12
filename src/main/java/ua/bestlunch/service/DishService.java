package ua.bestlunch.service;


import ua.bestlunch.model.Dish;
import ua.bestlunch.util.exception.NotFoundException;

import java.util.List;

public interface DishService {

    Dish get(int id) throws NotFoundException;

    Dish save(Dish dish);

    Dish update(Dish dish);

    void delete(int id) throws NotFoundException;

    List<Dish> getAllByLunch (int lunchId) throws NotFoundException;

    List<Dish> getAllByRestaurant(int restaurantId) throws NotFoundException;
}
