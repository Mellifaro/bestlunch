package ua.bestlunch.service;

import ua.bestlunch.model.Restaurant;
import ua.bestlunch.repository.RestaurantRepository;
import ua.bestlunch.repository.RestaurantRepositoryImpl;

import java.util.List;

/**
 * Created by Виктор on 13.08.2016.
 */
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository repository = new RestaurantRepositoryImpl();

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
