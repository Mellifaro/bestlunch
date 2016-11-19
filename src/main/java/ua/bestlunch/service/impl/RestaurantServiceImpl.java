package ua.bestlunch.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.to.RestaurantWithLunch;
import ua.bestlunch.repository.LunchRepository;
import ua.bestlunch.repository.RestaurantRepository;
import ua.bestlunch.repository.VoteRepository;
import ua.bestlunch.service.RestaurantService;
import ua.bestlunch.util.exception.ExceptionUtil;

import java.util.ArrayList;
import java.util.List;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private LunchRepository lunchRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Restaurant get(int id) {
        return ExceptionUtil.checkNotFoundWithId(restaurantRepository.find(id), id);
    }

    @Override
    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant update(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Override
    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<RestaurantWithLunch> getAllWithLunches() {
        List<Restaurant> restaurants = getAll();
        List<RestaurantWithLunch> withLunches = new ArrayList<>();

        for(Restaurant r : restaurants){
            Lunch lunch = lunchRepository.getCurrentLunch(r.getId());
            int votes = voteRepository.getVotesForRestaurant(r.getId());
            RestaurantWithLunch restaurantWithLunch = new RestaurantWithLunch(r, lunch, votes);
            withLunches.add(restaurantWithLunch);
        }
        return withLunches;
    }

    @Override
    @CacheEvict(value = "restaurants", allEntries = true)
    public void evictCache() {

    }
}
