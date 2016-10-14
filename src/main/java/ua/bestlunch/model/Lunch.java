package ua.bestlunch.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@NamedQueries({
        @NamedQuery(name = Lunch.DELETE, query = "DELETE FROM Lunch l WHERE l.id=:id"),
        @NamedQuery(name = Lunch.ALL_SORTED, query = "SELECT l FROM Lunch l ORDER BY l.name"),
        @NamedQuery(name = Lunch.BYRESTAURANT, query = "SELECT l FROM Lunch l WHERE l.restaurant.id=:restaurant_id ORDER BY l.name")
})
@Entity
@Table(name = "lunches")
public class Lunch extends NamedEntity{

    public static final String DELETE = "Lunch.delete";
    public static final String ALL_SORTED = "Lunch.getAllSorted";
    public static final String BYRESTAURANT = "Lunch.getByRestaurant";

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(name="lunch_dish",
            joinColumns = @JoinColumn(name = "lunch_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id")
    )
    private Set<Dish> dishes;
}
