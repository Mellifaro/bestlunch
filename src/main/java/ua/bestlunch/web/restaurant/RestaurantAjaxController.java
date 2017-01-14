package ua.bestlunch.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.to.RestaurantWithLunch;
import ua.bestlunch.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Виктор on 22.12.2016.
 */
@RestController
@RequestMapping(value = "ajax/restaurants")
public class RestaurantAjaxController {

    @Autowired
    private RestaurantService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantWithLunch> getAll(){
        return service.getAllWithLunches();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getById(@PathVariable("id") int id){
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@Valid Restaurant restaurant){
        if(restaurant.isNew()){
            service.save(restaurant);
        }else{
            service.update(restaurant);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }
}
