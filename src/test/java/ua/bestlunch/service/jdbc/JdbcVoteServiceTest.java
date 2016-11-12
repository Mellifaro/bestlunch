package ua.bestlunch.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ua.bestlunch.service.AbstractVoteServiceTest;

import static ua.bestlunch.Profiles.JDBC;

/**
 * Created by Виктор on 21.10.2016.
 */
@ActiveProfiles(JDBC)
public class JdbcVoteServiceTest extends AbstractVoteServiceTest{
}
