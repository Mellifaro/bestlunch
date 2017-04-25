package ua.bestlunch;

import ua.bestlunch.model.BaseEntity;
import ua.bestlunch.model.Role;

import java.util.Collections;
import java.util.Set;

/**
 * Created by Виктор on 18.04.2017.
 */
//todo delete this class/ It's duplicate of AuthorizedUser
public class LoggedUser {
    protected int id = BaseEntity.START_SEQ;

    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
    protected boolean enabled = true;

    private static LoggedUser LOGGED_USER = new LoggedUser();

    private static LoggedUser get(){
        return LOGGED_USER;
    }

    private static int getId(){
        return get().id;
    }

    public Set<Role> getAuthorities(){
        return roles;
    }

    public boolean isEnabled(){
        return enabled;
    }
}
