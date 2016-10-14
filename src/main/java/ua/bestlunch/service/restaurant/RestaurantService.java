package ua.bestlunch.service.restaurant;

import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.to.RestaurantWithLunch;

import java.util.List;

/**
 * Created by Виктор on 13.08.2016.
 */

public interface RestaurantService {

    List<Restaurant> getAll();

    Restaurant get(int id);

    Restaurant save(Restaurant restaurant);

    void delete(int id);

    RestaurantWithLunch getWithLunch(int id);

    List<RestaurantWithLunch> getAllWithLunches();
}
