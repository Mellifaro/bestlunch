package ua.bestlunch.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.bestlunch.LunchTestData;
import ua.bestlunch.RestaurantTestData;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.util.exception.NotFoundException;

import java.util.List;

import static ua.bestlunch.LunchTestData.*;

/**
 * Created by Виктор on 21.10.2016.
 */
public abstract class AbstractLunchServiceTest extends AbstractServiceTest{

    @Autowired
    protected LunchService service;

    @Test
    public void testGet(){
        Lunch found = service.get(100);
        LOG.info(found.toString());
        Assert.assertEquals(LUNCH1, found);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound(){
        service.get(1);
    }

    @Test
    public void testSave(){
        Lunch created = getCreated();
        service.save(created);
        LOG.info(created.toString());
        Assert.assertEquals(created, service.get(created.getId()));
    }

    @Test
    public void testUpdate(){
        Lunch updated = getUpdated();
        service.update(updated);
        Assert.assertEquals(updated, service.get(LUNCH1.getId()));
    }

    @Test
    public void testDelete(){
        int countRows = service.getAllByRestaurant(RestaurantTestData.RESTAURANT1.getId()).size();
        service.delete(LUNCH1.getId());
        int leftRows = service.getAllByRestaurant(RestaurantTestData.RESTAURANT1.getId()).size();
        Assert.assertEquals(leftRows, countRows - 1);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound(){
        service.delete(1);
    }

    @Test
    public void getAllByRestaurant(){
        List<Lunch> lunches = service.getAllByRestaurant(RestaurantTestData.RESTAURANT1.getId());
        lunches.forEach(l -> LOG.info(l.toString()));
        Assert.assertEquals(lunches.size(), 3);
    }

}
