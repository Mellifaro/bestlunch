package ua.bestlunch;

import ua.bestlunch.model.Role;
import ua.bestlunch.model.User;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Виктор on 01.11.2016.
 */
public class UserTestData {
    public static final User ADMIN = new User(100, "Admin", "admin@gmail.com", "admin", EnumSet.of(Role.ADMIN));
    public static final User USER1 = new User(101, "User", "user@gmail.com", "user", EnumSet.of(Role.USER));

    public static User getCreated(){
        return new User(null, "User", "user@gmail.com", "user", EnumSet.of(Role.USER));
    }

    public static User getUpdated(){
        return new User(100, "Andrew", "andrew@gmail.com", "user", EnumSet.of(Role.USER));
    }
}
