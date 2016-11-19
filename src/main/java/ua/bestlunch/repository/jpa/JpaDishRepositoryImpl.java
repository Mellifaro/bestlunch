package ua.bestlunch.repository.jpa;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Dish;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements DishRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish) {
        if(dish.isNew()){
            em.persist(dish);
            return dish;
        }else{
            return em.merge(dish);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Dish find(int id) {
        return em.find(Dish.class, id);
    }

    @Override
    //setParameter("lunch", lunch) - may be mistake
    public List<Dish> findAllByLunch(int lunchId) {
        Lunch lunch = em.find(Lunch.class, lunchId);
        return em.createNamedQuery(Dish.BYLUNCH, Dish.class).setParameter("lunch", lunch).getResultList();

        //Select necessary lunch
        //Lunch lunch = em.find(Lunch.class, id)
        //Select all dishes
        //SELECT d FROM Dish d WHERE :lunch IN elements(d.lunches)
    }

    @Override
    //restaurant.id  - may be a mistake. And in annotation too
    public List<Dish> findAllByRestaurant(int restaurantId) {
        Restaurant restaurant = em.find(Restaurant.class, restaurantId);
        return em.createNamedQuery(Dish.BYRESTAURANT, Dish.class).setParameter("restaurant", restaurant).getResultList();
    }
}
