package ua.bestlunch.service.jpa;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ua.bestlunch.repository.JpaUtil;
import ua.bestlunch.service.AbstractUserServiceTest;

import static ua.bestlunch.Profiles.JPA;

/**
 * Created by Виктор on 21.10.2016.
 */
@ActiveProfiles(JPA)
public class JpaUserServiceTest extends AbstractUserServiceTest{

    @Autowired
    private JpaUtil jpaUtil;

    @Before
    public void clearCache(){
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
