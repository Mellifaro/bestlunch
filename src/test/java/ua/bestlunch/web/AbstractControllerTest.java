package ua.bestlunch.web;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ua.bestlunch.repository.JpaUtil;

import javax.annotation.PostConstruct;

import static ua.bestlunch.Profiles.ACTIVE_DB;
import static ua.bestlunch.Profiles.DB_IMPLEMENTATION;

/**
 * Created by Виктор on 30.11.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles({ACTIVE_DB, DB_IMPLEMENTATION})
public abstract class AbstractControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    @Autowired
    private JpaUtil jpaUtil;

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct(){
        mockMvc = MockMvcBuilders
                    .webAppContextSetup(webApplicationContext)
                    .addFilter(CHARACTER_ENCODING_FILTER)
                      //.apply(springSecurity())
                    .build();
    }

    @Before
    public void setUp(){
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
