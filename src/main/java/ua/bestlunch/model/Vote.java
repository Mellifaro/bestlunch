package ua.bestlunch.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.user=:use AND v.time BETWEEN :startTime AND :endTime"),
        @NamedQuery(name = Vote.BYUSER, query = "SELECT v FROM Vote v WHERE v.user=:user"),
        @NamedQuery(name = Vote.BYRESTAURANT, query = "SELECT COUNT(v.id) FROM Vote v WHERE v.restaurant=:restaurant AND v.time BETWEEN :startTime AND :endTime"),
        @NamedQuery(name = Vote.CURRENTVOTE, query = "SELECT v FROM Vote v WHERE v.time BETWEEN :startTime AND :endTime"),
})
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity{

    public static final String DELETE = "Vote.delete";
    public static final String BYUSER = "Vote.by_user";
    public static final String BYRESTAURANT = "Vote.by_restaurant";
    public static final String CURRENTVOTE = "Vote.currentVote";

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

    public Vote(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, User user, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDateTime time) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.time = time;
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
}
