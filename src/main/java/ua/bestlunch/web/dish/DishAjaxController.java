package ua.bestlunch.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.bestlunch.model.Dish;
import ua.bestlunch.service.DishService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Виктор on 25.12.2016.
 */
@RestController
@RequestMapping(value = "/ajax/restaurants/{rest_id}")
public class DishAjaxController {

    @Autowired
    private DishService service;

    @RequestMapping(value = "/dishes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllByRestaurant(@PathVariable("rest_id") int rest_id){
        return service.getAllByRestaurant(rest_id);
    }

    @RequestMapping(value = "/dishes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish getById(@PathVariable("id") int id){
        return service.get(id);
    }

    @RequestMapping(value = "/lunches/{lunch_id}/dishes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllByLunch(@PathVariable("lunch_id") int lunch_id){
        return service.getAllByLunch(lunch_id);
    }

    @RequestMapping(value = "/lunches/{lunch_id}/dishes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish getByIdFromlunches(@PathVariable("id") int id){
        return service.get(id);
    }

    @RequestMapping(value = "/dishes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid Dish dish){
        if(dish.isNew()){
            service.save(dish);
        }else{
            service.update(dish);
        }
    }

    @RequestMapping(value = "/lunches/{lunch_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateFromLunches(@Valid Dish dish){
        if(dish.isNew()){
            service.save(dish);
        }else{
            service.update(dish);
        }
    }

    @RequestMapping(value = "/dishes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }
}
