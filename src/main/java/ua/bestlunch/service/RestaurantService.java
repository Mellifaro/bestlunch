package ua.bestlunch.service;

import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.to.RestaurantWithLunch;
import ua.bestlunch.util.exception.NotFoundException;

import java.util.List;



public interface RestaurantService {

    Restaurant get(int id) throws NotFoundException;

    Restaurant save(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    List<Restaurant> getAll();

    List<RestaurantWithLunch> getAllWithLunches();
}
