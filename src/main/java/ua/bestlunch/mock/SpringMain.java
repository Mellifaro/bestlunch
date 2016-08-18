package ua.bestlunch.mock;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Виктор on 16.08.2016.
 */
public class SpringMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for(String bean : beanDefinitionNames){
            System.out.println("-----" + bean);
        }
    }
}
