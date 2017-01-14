package ua.bestlunch.web.lunch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.service.LunchService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Виктор on 23.12.2016.
 */
@RestController
@RequestMapping(value = "/ajax/restaurants/{rest_id}/lunches")
public class LunchAjaxController {

    @Autowired
    private LunchService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lunch> getAllByRestaurant(@PathVariable("rest_id") int rest_id){
        return service.getAllByRestaurant(rest_id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Lunch get(@PathVariable("id") int id){
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lunch> create(@Valid @RequestBody Lunch lunch){
        Lunch created = service.save(lunch);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/restaurants/{rest_id}/lunches/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid Lunch lunch){
        service.update(lunch);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }
}
