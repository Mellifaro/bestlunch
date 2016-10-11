package ua.bestlunch.repository;

import ua.bestlunch.model.Dish;
import ua.bestlunch.model.Restaurant;

import java.util.List;

/**
 * Created by Виктор on 13.09.2016.
 */
public interface DishRepository {

    Dish save(Dish dish);

    void delete(int id);

    Dish find(int id);

    List<Dish> findAllByLunch();

    List<Dish> findAllByRestaurant();
}
