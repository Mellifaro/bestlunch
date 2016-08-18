package ua.bestlunch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.repository.RestaurantRepository;
import ua.bestlunch.repository.RestaurantRepositoryImpl;

import java.util.List;

/**
 * Created by Виктор on 13.08.2016.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired
    private RestaurantRepository repository;

    @Override
    public List<Restaurant> getAll() {
        LOG.debug("Repository - " + repository.toString());
        return repository.getAll();
    }
}
