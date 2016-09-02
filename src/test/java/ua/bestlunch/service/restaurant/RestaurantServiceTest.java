package ua.bestlunch.service.restaurant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Виктор on 23.08.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-app.xml");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for(String bean : beanDefinitionNames){
            System.out.println("-----" + bean);
        }
        service.delete(102);
    }

}