package ua.bestlunch.repository;

import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Restaurant;

import java.util.List;

/**
 * Created by Виктор on 13.09.2016.
 */
public interface LunchRepository {

    Lunch save(Lunch lunch);

    boolean delete(int id);

    Lunch get(int id);

    List<Lunch> getAll();

    List<Lunch> getAllByRestaurant(int restaurantId);
}
