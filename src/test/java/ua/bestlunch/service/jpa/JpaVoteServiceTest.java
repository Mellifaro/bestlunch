package ua.bestlunch.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ua.bestlunch.service.AbstractUserServiceTest;
import ua.bestlunch.service.AbstractVoteServiceTest;

import static ua.bestlunch.Profiles.JPA;

/**
 * Created by Виктор on 21.10.2016.
 */
@ActiveProfiles(JPA)
public class JpaVoteServiceTest extends AbstractVoteServiceTest {
}
