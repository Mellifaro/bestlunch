package ua.bestlunch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.repository.RestaurantRepository;
import ua.bestlunch.repository.UserRepository;
import ua.bestlunch.repository.VoteRepository;
import ua.bestlunch.service.VoteService;
import ua.bestlunch.util.exception.IllegalAccessException;

import java.util.List;

/**
 * Created by Виктор on 19.10.2016.
 */
@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Vote getCurrentVote(User user) {
        return voteRepository.getCurrentVote(user);
    }

    @Override
    public Vote addVote(User user, int restId) {
        Restaurant restaurant = restaurantRepository.find(restId);
        Vote currentVote = getCurrentVote(user);
        if(currentVote != null){
            throw new IllegalAccessException("This user have already voted today for " + currentVote.getRestaurant().getName());
        }
        Vote created = new Vote(user, restaurant);
        return voteRepository.saveVote(created);
    }

    @Override
    public void cancelVote(User user) {
        Vote currentVote = voteRepository.getCurrentVote(user);
        if(currentVote == null){
            throw new IllegalAccessException("User can't delete vote because he hasn't voted yet");
        }else if(currentVote.getTime().getHour() > 11){
            throw new IllegalAccessException("User can't cancel his vote after 11:00 a.m.");
        }
        voteRepository.deleteVote(user);
    }

    @Override
    public List<Vote> findAllByUser(User user) {
        return voteRepository.getAllByUser(user);
    }

    @Override
    public int countVotesByRestaurant(Restaurant restaurant){
        return voteRepository.getVotesForRestaurant(restaurant.getId());
    }
}
