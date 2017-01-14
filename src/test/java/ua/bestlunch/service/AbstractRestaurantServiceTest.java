package ua.bestlunch.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.util.exception.NotFoundException;

import java.util.List;

import static ua.bestlunch.RestaurantTestData.*;

/**
 * Created by Виктор on 21.10.2016.
 */
//TODO CORRECT ALL TEST TO MATCHERS
public abstract class AbstractRestaurantServiceTest extends AbstractServiceTest{

    @Autowired
    protected RestaurantService service;

    @Before
    public void setUp(){
        service.evictCache();
    }

    @Test
    public void testGet(){
        Restaurant found = service.get(100);
        LOG.info(RESTAURANT1.toString());
        LOG.info(found.toString());

        Assert.assertEquals(RESTAURANT1, found);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound(){
        service.get(1);
    }

    @Test
    public void testSave(){
        Restaurant created = getCreated();
        service.save(created);
        LOG.info(created.toString());

        Assert.assertEquals(created, service.get(created.getId()));
    }

    @Test
    public void testUpdate(){
        Restaurant updated = getUpdated();
        LOG.info(updated.toString());
        service.update(updated);

        Assert.assertEquals(updated, service.get(RESTAURANT1.getId()));
    }

    @Test
    public void testDelete(){
        int countRows = service.getAll().size();
        service.delete(RESTAURANT1.getId());
        int leftRows = service.getAll().size();

        Assert.assertEquals(leftRows, countRows-1);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound(){
        service.delete(1);
    }

    @Test
    public void testGetAll(){
        List<Restaurant> restaurants = service.getAll();
        Assert.assertEquals(4, restaurants.size());
    }

    @Test
    @Ignore
    public void testGetAllWithLunches(){

    }
}
