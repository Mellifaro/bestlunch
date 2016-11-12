package ua.bestlunch;

import ua.bestlunch.model.to.UserTo;

/**
 * Created by Виктор on 12.11.2016.
 */
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;
}
