package ua.bestlunch.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.bestlunch.DishTestData;
import ua.bestlunch.LunchTestData;
import ua.bestlunch.RestaurantTestData;
import ua.bestlunch.model.Dish;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.util.exception.NotFoundException;

import java.util.List;

import static ua.bestlunch.DishTestData.*;

/**
 * Created by Виктор on 21.10.2016.
 */

//TODO CORRECT ALL TEST TO MATCHERS
public abstract class AbstractDishServiceTest extends AbstractServiceTest{

    @Autowired
    protected DishService service;

    @Test
    public void testGet(){
        Dish found = service.get(100);
        LOG.info(found.toString());
        Assert.assertEquals(found, DISH1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound(){
        service.get(1);
    }

    @Test
    public void testSave(){
        Dish created = getCreated();
        service.save(created);
        LOG.info(created.toString());
        Assert.assertEquals(created, service.get(created.getId()));
    }

    @Test
    public void testUpdate(){
        Dish updated = getUpdated();
        service.update(updated);
        Assert.assertEquals(updated, service.get(DISH1.getId()));
    }

    @Test
    public void testDelete(){
        int countRows = service.getAllByRestaurant(RestaurantTestData.RESTAURANT1.getId()).size();
        service.delete(DISH1.getId());
        int leftRows = service.getAllByRestaurant(RestaurantTestData.RESTAURANT1.getId()).size();
        Assert.assertEquals(leftRows, countRows - 1);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound(){
        service.delete(1);
    }

    @Test
    public void testGetAllByLunch(){
        List<Dish> dishes = service.getAllByLunch(LunchTestData.LUNCH1.getId());
        dishes.forEach(l -> LOG.info(l.toString()));
        Assert.assertEquals(2, dishes.size());
    }

    @Test
    public void testGetAllByRestaurant(){
        List<Dish> dishes = service.getAllByRestaurant(RestaurantTestData.RESTAURANT1.getId());
        dishes.forEach(l -> LOG.info(l.toString()));
        Assert.assertEquals(2, dishes.size());
    }
}
