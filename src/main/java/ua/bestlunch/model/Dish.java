package ua.bestlunch.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Виктор on 18.08.2016.
 */
@Entity
@Table(name = "dishes")
public class Dish extends NamedEntity{

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dishes")
    private Set<Lunch> lunches;
}
