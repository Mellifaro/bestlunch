package ua.bestlunch.repository;

/**
 * Created by Виктор on 13.09.2016.
 */
public interface VoteRepository {

    boolean createVote(int id_user, int id_restaurant);

    boolean cancelVote(int id_user);
}
