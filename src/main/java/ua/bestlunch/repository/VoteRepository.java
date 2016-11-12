package ua.bestlunch.repository;

import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;


public interface VoteRepository {

    Vote saveVote(Vote vote);

    boolean deleteVote(User user);

    List<Vote> getAllByUser(User user);

    int getVotesForRestaurant(int restaurantId);

    Vote getCurrentVote(User user);
}
