package ua.bestlunch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.bestlunch.model.Role;
import ua.bestlunch.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = {"classpath:db/initDB_postgres.sql", "classpath:db/populateDB.sql"},
        config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public abstract class UserServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService service;


    @Test
    public void save() throws Exception {
        LOG.debug("Saving user begin....\n");
        LOG.debug("-------------------------");

        User user = new User();
        user.setName("Tirrion");
        user.setEmail("Tirrion@gmail.com");
        user.setPassword("Lannister");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        service.save(user);

        List<User> users = service.getAll();
        users.forEach(System.out::println);

        LOG.debug("-------------------------");
        LOG.debug("Finishing saving user....\n");

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void getByEmail() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

}