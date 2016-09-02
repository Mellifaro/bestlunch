package ua.bestlunch.repository;

import ua.bestlunch.model.Restaurant;

import java.util.List;

/**
 * Created by Виктор on 13.08.2016.
 */
public interface RestaurantRepository {

    List<Restaurant> getAll();

    Restaurant get(int id);

    Restaurant save(Restaurant restaurant);

    void delete(int id);
}
