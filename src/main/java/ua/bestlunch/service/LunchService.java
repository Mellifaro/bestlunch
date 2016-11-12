package ua.bestlunch.service;


import ua.bestlunch.model.Lunch;
import ua.bestlunch.util.exception.NotFoundException;

import java.util.List;

public interface LunchService {

    Lunch get(int id) throws NotFoundException;

    Lunch save(Lunch lunch);

    Lunch update(Lunch lunch);

    void delete(int lunchId) throws NotFoundException;

    List<Lunch> getAllByRestaurant(int restaurantId);
}
