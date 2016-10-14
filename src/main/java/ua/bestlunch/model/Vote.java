package ua.bestlunch.model;


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
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private Restaurant restaurant;

}
