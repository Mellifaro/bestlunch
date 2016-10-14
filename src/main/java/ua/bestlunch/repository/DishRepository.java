package ua.bestlunch.repository;

import ua.bestlunch.model.Dish;
import ua.bestlunch.model.Restaurant;

import java.util.List;


public interface DishRepository {

    Dish save(Dish dish);

    boolean delete(int id);

    Dish find(int id);

    List<Dish> findAllByLunch(int lunchId);

    List<Dish> findAllByRestaurant(int restaurantId);
}
