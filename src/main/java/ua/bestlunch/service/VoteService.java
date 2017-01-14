package ua.bestlunch.service;


import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;

import java.util.List;

public interface VoteService {

    Vote addVote(User user, int restId);

    void cancelVote(User user);

    Vote getCurrentVote(User user);

    List<Vote> findAllByUser(User user);

    int countVotesByRestaurant(Restaurant restaurant);
}
