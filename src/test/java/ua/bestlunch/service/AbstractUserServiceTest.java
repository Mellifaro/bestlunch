package ua.bestlunch.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Role;
import ua.bestlunch.model.User;
import ua.bestlunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;

/**
 * Created by Виктор on 21.10.2016.
 */
public class AbstractUserServiceTest extends AbstractServiceTest{

    @Autowired
    protected UserService service;

    @Test
    @Transactional
    public void testSave(){
        User newUser = new User(null, "New User", "newuser@gmail.com", "passwerd", EnumSet.of(Role.USER, Role.ADMIN));
        User created = service.save(newUser);
        newUser.setId(created.getId());

        Assert.assertEquals(newUser.getEmail(), service.get(newUser.getId()).getEmail());
        User last = service.get(newUser.getId());
        LOG.debug(last.toString());
    }

    @Test(expected = DataAccessException.class)
    @Transactional
    @Rollback
    public void testDuplicateNameSave(){
        User newUser = new User(null, "Admin", "newuser@gmail.com", "passwerd", EnumSet.of(Role.USER, Role.ADMIN));
        User created = service.save(newUser);
    }

    @Test(expected = DataAccessException.class)
    @Transactional
    public void testDuplicateMailSave(){
        User newUser = new User(null, "New User", "admin@gmail.com", "passwerd", EnumSet.of(Role.USER, Role.ADMIN));
        User created = service.save(newUser);
    }

    @Test
    public void testDelete(){
        int beginRows = service.getAll().size();
        service.delete(101);
        int endRows = service.getAll().size();

        Assert.assertEquals(endRows, (beginRows - 1));
    }

    //+
    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete(){
        service.delete(1);
    }

    @Test
    public void testGet(){
        User newUser = new User(null, "NewUser", "newuser@gmail.com", "passwerd", EnumSet.of(Role.USER, Role.ADMIN));
        User created = service.save(newUser);
        newUser.setId(created.getId());

        LOG.info(newUser.toString());
        LOG.info(created.toString());
        Assert.assertEquals(newUser, created);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound(){
        service.get(1);
    }

    @Test
    public void testGetByEmail(){
        User newUser = new User(null, "NewUser", "newuser@gmail.com", "passwerd", EnumSet.of(Role.USER, Role.ADMIN));
        User created = service.save(newUser);
        User byEmail = service.getByEmail("newuser@gmail.com");

        Assert.assertEquals(created, byEmail);
    }

    @Test
    public void testGetAll(){
        int size = service.getAll().size();
        Assert.assertEquals(size, 2);
    }

    @Test
    public void testUpdate(){
        User newUser = new User(null, "NewUser", "newuser@gmail.com", "passwerd", EnumSet.of(Role.USER, Role.ADMIN));
        User created = service.save(newUser);
        created.setName("LastUser");
        created.setEmail("lastuser@yahoo.com");
        created.setRoles(EnumSet.of(Role.USER));

        service.update(created);
        Assert.assertEquals(created, service.get(created.getId()));
    }
}
