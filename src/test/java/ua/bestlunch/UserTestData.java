package ua.bestlunch;

import ua.bestlunch.matcher.ModelMatcher;
import ua.bestlunch.model.Role;
import ua.bestlunch.model.User;

import java.util.EnumSet;
import java.util.Objects;

/**
 * Created by Виктор on 01.11.2016.
 */
public class UserTestData {

    public static final int ADMIN_ID = 100;
    public static final int USER_ID = 101;

    public static final User ADMIN = new User(100, "Admin", "admin@gmail.com", "admin", EnumSet.of(Role.ROLE_ADMIN));
    public static final User USER1 = new User(101, "User", "user@gmail.com", "user", EnumSet.of(Role.ROLE_USER));

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>(User.class,
            (expected, actual) -> {
                if (expected == actual) {
                    return true;
                }
                boolean cmp = Objects.equals(expected.getPassword(), actual.getPassword())
                        && Objects.equals(expected.getId(), actual.getId())
                        && Objects.equals(expected.getName(), actual.getName())
                        && Objects.equals(expected.getEmail(), actual.getEmail())
                      //  && Objects.equals(expected.isEnabled(), actual.isEnabled())
                        && Objects.equals(expected.getRoles(), actual.getRoles());
                return cmp;
            }
    );

    public static User getCreated(){
        return new User(null, "User", "user@gmail.com", "user", EnumSet.of(Role.ROLE_USER));
    }

    public static User getUpdated(){
        return new User(100, "Andrew", "andrew@gmail.com", "user", EnumSet.of(Role.ROLE_USER));
    }
}
