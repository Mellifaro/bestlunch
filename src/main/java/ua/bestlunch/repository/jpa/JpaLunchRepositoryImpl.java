package ua.bestlunch.repository.jpa;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Vote;
import ua.bestlunch.repository.LunchRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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
        return em.createNamedQuery(Lunch.DELETE).setParameter("id", id).executeUpdate() != 0;
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

    @Override
    public Lunch getCurrentLunch(int restaurantId) {
        LocalDate before = LocalDate.now();
        LocalDate after = before.plusDays(1);

        List<Lunch> lunches = em.createNamedQuery(Lunch.CURRENTLUNCH, Lunch.class)
                .setParameter("startTime", before)
                .setParameter("endTime", after)
                .getResultList();
        if(lunches.size() > 1){
            throw new IllegalStateException("There are too many current lunches in database");
        }
        return lunches.isEmpty() ? null : lunches.get(0);
    }
}
