package ua.bestlunch.repository;

import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;


public interface VoteRepository {

    Vote addVote(Vote vote);

    boolean deleteVote(User user);
}
