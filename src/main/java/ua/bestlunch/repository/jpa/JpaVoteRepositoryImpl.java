package ua.bestlunch.repository.jpa;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepositoryImpl implements VoteRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote saveVote(Vote vote) {
        em.persist(vote);
        return vote;
    }

    @Override
    @Transactional
    public boolean deleteVote(User user) {
        LocalDate before = LocalDate.now();
        LocalDate after = before.plusDays(1);
        return em.createNamedQuery(Vote.DELETE, Vote.class)
                    .setParameter("user", user)
                    .setParameter("startTime", before)
                    .setParameter("endTime", after)
                    .executeUpdate()!= 0;
    }

    @Override
    public List<Vote> getAllByUser(User user) {
        return em.createNamedQuery(Vote.BYUSER, Vote.class).getResultList();
    }

    @Override
    public int getVotesForRestaurant(int restaurantId) {
        Query query =  em.createNamedQuery(Vote.BYRESTAURANT);
        Restaurant restaurant = em.find(Restaurant.class, restaurantId);
        LocalDate before = LocalDate.now();
        LocalDate after = before.plusDays(1);

        Object[] results = (Object[]) query.setParameter("restaurant", restaurant)
                                                .setParameter("startTime", before)
                                                .setParameter("endTime", after)
                                                .getSingleResult();
        return (Integer) results[0];
    }

    @Override
    public Vote getCurrentVote(User user) {
        LocalDate before = LocalDate.now();
        LocalDate after = before.plusDays(1);
        List<Vote> votes = em.createNamedQuery(Vote.CURRENTVOTE, Vote.class)
                .setParameter("startTime", before)
                .setParameter("endTime", after)
                .getResultList();
        if(votes.size() > 1){
            throw new IllegalStateException("There are too many current votes in database");
        }
        return votes.isEmpty() ? null : votes.get(0);
    }
}
