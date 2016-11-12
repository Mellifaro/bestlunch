package ua.bestlunch.repository.dataJpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Lunch;

import java.util.List;

@Transactional(readOnly = true)
public interface ProxyLunchRepository extends JpaRepository<Lunch, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    //May be a mistake
    @Query("SELECT l FROM Lunch l WHERE l.restaurant.id=:restaurant_id ORDER BY l.name")
    List<Lunch> getAllByRestaurant(int restaurantId);
}
