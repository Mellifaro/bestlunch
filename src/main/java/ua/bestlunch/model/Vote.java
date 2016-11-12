package ua.bestlunch.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.user=:user")
})
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity{

    public static final String DELETE = "Vote.delete";

    @Column(name = "vote_time", columnDefinition = "timestamp default now()")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Vote(){}

    public Vote(Integer id, User user, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + super.id + '\'' +
                ", time=" + time +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;
        if (!super.equals(o)) return false;

        Vote vote = (Vote) o;

        if (!time.equals(vote.time)) return false;
        if (!user.equals(vote.user)) return false;
        return restaurant.equals(vote.restaurant);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + restaurant.hashCode();
        return result;
    }
}
