package ua.bestlunch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.repository.VoteRepository;
import ua.bestlunch.service.VoteService;
import ua.bestlunch.util.exception.ExceptionUtil;
import ua.bestlunch.util.exception.IllegalAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Виктор on 19.10.2016.
 */
@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote addVote(Vote vote) {
        Vote checked = repository.getCurrentVote(vote.getUser());
        if(checked != null){
            throw new IllegalAccessException("This user have already voted today for " + vote.getRestaurant().getName());
        }
        return repository.saveVote(vote);
    }

    @Override
    public void cancelVote(User user) {
        Vote currentVote = repository.getCurrentVote(user);
        if(currentVote == null){
            throw new IllegalAccessException("User can't delete vote because he hasn't voted yet");
        }else if(currentVote.getTime().getHour() > 11){
            throw new IllegalAccessException("User can't cancel his vote after 11:00 a.m.");
        }
        repository.deleteVote(user);
    }

    @Override
    public List<Vote> findAllByUser(User user) {
        return repository.getAllByUser(user);
    }

    @Override
    public int countVotesByRestaurant(Restaurant restaurant){
        return repository.getVotesForRestaurant(restaurant.getId());
    }

}
