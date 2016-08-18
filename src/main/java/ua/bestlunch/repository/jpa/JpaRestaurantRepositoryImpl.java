package ua.bestlunch.repository.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Виктор on 16.08.2016.
 */
@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepositoryImpl implements RestaurantRepository {
    private static final Logger LOG = LoggerFactory.getLogger(JpaRestaurantRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Restaurant> getAll() {
        LOG.debug("Entity manager - " + em.toString());
        return em.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class).getResultList();
    }
}
