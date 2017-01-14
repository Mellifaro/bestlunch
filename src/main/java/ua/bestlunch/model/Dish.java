package ua.bestlunch.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.BYLUNCH, query = "SELECT d FROM Dish d WHERE :lunch IN elements(d.lunches)"),
        @NamedQuery(name = Dish.BYRESTAURANT, query = "SELECT d FROM Dish d WHERE d.restaurant=:restaurant")
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
    private List<Lunch> lunches;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Dish(){}

    public Dish(Integer id, String name, BigDecimal price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Lunch> getLunches() {
        return lunches;
    }

    public void setLunches(List<Lunch> lunches) {
        this.lunches = lunches;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + super.id + '\'' +
                ", name='" + super.name + '\'' +
                ", price=" + price +
                ", lunches=" + lunches +
                ", restaurant=" + restaurant +
                '}';
    }
}
