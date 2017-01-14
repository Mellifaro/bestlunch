package ua.bestlunch.util;

import ua.bestlunch.model.Role;
import ua.bestlunch.model.User;
import ua.bestlunch.model.to.UserTo;

import java.util.EnumSet;

/**
 * Created by Виктор on 14.01.2017.
 */
public class UserUtil {

    public static User createFromTo(UserTo newUser){
        return new User(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getPassword(), EnumSet.of(Role.ROLE_USER));
    }

    public static UserTo asTo(User user){
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static void updateFromTo(User user, UserTo userTo){
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
    }
}
