package ua.bestlunch.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private List<Dish> dishes;

    public Lunch(){

    }

    public Lunch(Integer id, String name, BigDecimal price, LocalDateTime dateTime) {
        super(id, name);
        this.price = price;
        this.dateTime = dateTime;
        this.dishes = new ArrayList<>();
    }

    public Lunch(Integer id, String name, BigDecimal price, LocalDateTime dateTime, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
        this.dishes = new ArrayList<>();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Lunch{" +
                "id='" + super.id + '\'' +
                ", name='" + super.name + '\'' +
                ", price=" + price +
                ", dateTime=" + dateTime +
                ", restaurant=" + restaurant +
                ", dishes=" + dishes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lunch)) return false;
        if (!super.equals(o)) return false;

        Lunch lunch = (Lunch) o;

        if (!price.equals(lunch.price)) return false;
        return dateTime.equals(lunch.dateTime);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + dateTime.hashCode();
        return result;
    }
}
