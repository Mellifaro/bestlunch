package ua.bestlunch.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ua.bestlunch.service.AbstractUserServiceTest;

import static ua.bestlunch.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest{
}
