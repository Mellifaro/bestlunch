package ua.bestlunch.repository;

import ua.bestlunch.model.Restaurant;

import java.util.List;

/**
 * Created by Виктор on 13.08.2016.
 */
public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    void delete(int id);

    Restaurant find(int id);

    List<Restaurant> findAll();
}
