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
@RequestMapping(value = "/ajax/restaurants/{rest_id}/dishes")
public class DishAjaxController {

    @Autowired
    private DishService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable("id") int id){
        return service.get(id);
    }

    @RequestMapping(value = "/lunches/{lunch_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllByLunch(@PathVariable("lunch_id") int lunch_id){
        return service.getAllByLunch(lunch_id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllByRestaurant(@PathVariable("rest_id") int rest_id){
        return service.getAllByRestaurant(rest_id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish){
        Dish created = service.save(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/restaurants/{rest_id}/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid Dish dish){
        service.update(dish);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }
}
