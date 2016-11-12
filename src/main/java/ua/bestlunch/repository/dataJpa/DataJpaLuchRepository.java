package ua.bestlunch.repository.dataJpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.repository.LunchRepository;

import java.util.List;

@Repository
public class DataJpaLuchRepository implements LunchRepository{

    @Autowired
    private ProxyLunchRepository proxy;

    @Override
    public Lunch save(Lunch lunch) {
        return proxy.save(lunch);
    }

    @Override
    public boolean delete(int id) {
        return proxy.delete(id) != 0;
    }

    @Override
    public Lunch get(int id) {
        return proxy.findOne(id);
    }

    @Override
    public List<Lunch> getAll() {
        return proxy.findAll();
    }

    @Override
    public List<Lunch> getAllByRestaurant(int restaurantId) {
        return proxy.getAllByRestaurant(restaurantId);
    }

    @Override
    public Lunch getCurrentLunch(int restaurantId) {
        return null;
    }
}
