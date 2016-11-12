package ua.bestlunch.repository.dataJpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Dish;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface ProxyDishRepository extends JpaRepository<Dish, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    //may be a mistake
    @Query("SELECT d FROM Dish d WHERE :lunch IN elements(d.lunches)")
    List<Dish> findAllByLunch(@Param("lunch") Lunch lunch);

    //may be a mistake
    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> findAllByRestaurant(@Param("restaurantId") int restaurantId);
}
