package ua.bestlunch.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.BYLUNCH, query = "SELECT d FROM Dish d WHERE :lunch IN elements(d.lunches)"),
        @NamedQuery(name = Dish.BYRESTAURANT, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
})
@Entity
@Table(name = "dishes")
public class Dish extends NamedEntity{

    public static final String BYLUNCH = "Dish.byLunch";
    public static final String BYRESTAURANT = "Dish.byRestaurant";
    public static final String DELETE = "Dish.delete";

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dishes")
    private Set<Lunch> lunches;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
