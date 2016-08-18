package ua.bestlunch.model;


import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Виктор on 18.08.2016.
 */
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity{

    @Column(name = "vote_time", columnDefinition = "timestamp default now()")
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private Lunch lunch;

}
