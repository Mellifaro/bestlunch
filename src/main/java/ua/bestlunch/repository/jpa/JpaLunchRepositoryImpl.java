package ua.bestlunch.repository.jpa;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.repository.LunchRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaLunchRepositoryImpl implements LunchRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Lunch save(Lunch lunch) {
        if(lunch.isNew()){
            em.persist(lunch);
            return lunch;
        }else{
            return em.merge(lunch);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Lunch.DELETE, Lunch.class).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Lunch get(int id) {
        return em.find(Lunch.class, id);
    }

    @Override
    public List<Lunch> getAll() {
        return em.createNamedQuery(Lunch.ALL_SORTED, Lunch.class).getResultList();
    }

    @Override
    public List<Lunch> getAllByRestaurant(int restaurantId) {
        return em.createNamedQuery(Lunch.BYRESTAURANT, Lunch.class).setParameter("restaurant_id", restaurantId).getResultList();
    }
}
