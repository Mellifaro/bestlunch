package ua.bestlunch.web.lunch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.service.LunchService;
import ua.bestlunch.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Виктор on 23.12.2016.
 */
@RestController
@RequestMapping(value = "/ajax/restaurants/{rest_id}/lunches")
public class LunchAjaxController {

    @Autowired
    private LunchService lunchService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lunch> getAllByRestaurant(@PathVariable("rest_id") int rest_id){
        return lunchService.getAllByRestaurant(rest_id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Lunch get(@PathVariable("id") int id){
        return lunchService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@Valid Lunch lunch, @PathVariable("rest_id") int rest_id){
        Restaurant restaurant = restaurantService.get(rest_id);
        lunch.setRestaurant(restaurant);
        if(lunch.isNew()){
            lunchService.save(lunch);
        }else{
            lunchService.update(lunch);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        lunchService.delete(id);
    }
}
