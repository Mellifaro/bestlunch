package ua.bestlunch.repository.dataJpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository{

    @Autowired
    private ProxyRestaurantRepository proxy;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return proxy.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return proxy.delete(id) != 0;
    }

    @Override
    public Restaurant find(int id) {
        return proxy.findOne(id);
    }

    @Override
    public List<Restaurant> findAll() {
        return proxy.findAll();
    }
}
