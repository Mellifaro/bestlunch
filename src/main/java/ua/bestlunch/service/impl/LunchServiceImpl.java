package ua.bestlunch.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.repository.LunchRepository;
import ua.bestlunch.service.LunchService;
import ua.bestlunch.util.exception.ExceptionUtil;

import java.util.List;

@Service
public class LunchServiceImpl implements LunchService{

    @Autowired
    private LunchRepository repository;

    @Override
    public Lunch get(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Lunch save(Lunch lunch) {
        return repository.save(lunch);
    }

    @Override
    public Lunch update(Lunch lunch) {
        return repository.save(lunch);
    }

    @Override
    public void delete(int lunchId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(lunchId), lunchId);
    }

    @Override
    public List<Lunch> getAllByRestaurant(int restaurantId) {
        return repository.getAllByRestaurant(restaurantId);
    }
}
