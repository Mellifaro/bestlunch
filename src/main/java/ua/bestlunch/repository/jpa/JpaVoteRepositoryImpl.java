package ua.bestlunch.repository.jpa;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepositoryImpl implements VoteRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Vote saveVote(Vote vote) {
        em.persist(vote);
        return vote;
    }

    @Override
    public boolean deleteVote(User user) {
        return em.createNamedQuery(Vote.DELETE, Vote.class).setParameter("user", user).executeUpdate() != 0;
    }

    @Override
    public List<Vote> getAllByUser(User user) {
        return null;
    }

    @Override
    public int getVotesForRestaurant(int restaurantId) {
        return 0;
    }

    @Override
    public Vote getCurrentVote(User user) {
        return null;
    }
}
