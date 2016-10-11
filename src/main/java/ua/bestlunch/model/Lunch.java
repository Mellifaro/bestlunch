package ua.bestlunch.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Виктор on 18.08.2016.
 */
@Entity
@Table(name = "lunches")
public class Lunch extends NamedEntity{

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
