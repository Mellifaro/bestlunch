package ua.bestlunch.repository.dataJpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.repository.VoteRepository;

import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository{

    @Autowired
    private ProxyVoteRepository proxy;

    @Override
    public Vote saveVote(Vote vote) {
        return proxy.save(vote);
    }

    @Override
    public boolean deleteVote(User user) {
        return proxy.delete(user) != 0;
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
