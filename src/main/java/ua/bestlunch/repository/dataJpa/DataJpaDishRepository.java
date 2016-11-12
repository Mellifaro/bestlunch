package ua.bestlunch.repository.dataJpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.bestlunch.model.Dish;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.repository.DishRepository;

import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository{

    @Autowired
    private ProxyDishRepository proxy;

    @Autowired ProxyLunchRepository proxyLunch;

    @Override
    public Dish save(Dish dish) {
        return proxy.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return proxy.delete(id) != 0;
    }

    @Override
    public Dish find(int id) {
        return proxy.findOne(id);
    }

    @Override
    public List<Dish> findAllByLunch(int lunchId) {
        Lunch lunch = proxyLunch.findOne(lunchId);
        return proxy.findAllByLunch(lunch);
    }

    @Override
    public List<Dish> findAllByRestaurant(int restaurantId) {
        return proxy.findAllByRestaurant(restaurantId);
    }
}
