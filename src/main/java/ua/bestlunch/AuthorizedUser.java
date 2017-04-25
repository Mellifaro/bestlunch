package ua.bestlunch;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.bestlunch.model.User;
import ua.bestlunch.model.to.UserTo;
import ua.bestlunch.util.UserUtil;

import static java.util.Objects.requireNonNull;

/**
 * Created by Виктор on 12.11.2016.
 */

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private final static long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user){
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public static AuthorizedUser safeGet(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null){
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser)principal : null;
    }

    public static AuthorizedUser get(){
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return get().userTo.getId();
    }

    public void update(UserTo newTo){
        userTo = newTo;
    }

    public UserTo getUserTo(){
        return userTo;
    }
}
