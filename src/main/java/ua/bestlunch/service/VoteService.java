package ua.bestlunch.service;


import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;

import java.util.List;

public interface VoteService {

    Vote addVote(Vote vote);

    void cancelVote(User user);

    List<Vote> findAllByUser(User user);

    int countVotesByRestaurant(Restaurant restaurant);
}
