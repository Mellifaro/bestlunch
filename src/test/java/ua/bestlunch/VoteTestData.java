package ua.bestlunch;

import ua.bestlunch.model.Vote;

import static ua.bestlunch.RestaurantTestData.*;
import static ua.bestlunch.UserTestData.ADMIN;
import static ua.bestlunch.UserTestData.USER1;

/**
 * Created by Виктор on 01.11.2016.
 */
public class VoteTestData {

    public static final Vote VOTE1 = new Vote(100, ADMIN, RESTAURANT2);
    public static final Vote VOTE2 = new Vote(101, USER1, RESTAURANT4);

    public static Vote getCreated(){
        return new Vote(null, ADMIN, RESTAURANT3);
    }
}
