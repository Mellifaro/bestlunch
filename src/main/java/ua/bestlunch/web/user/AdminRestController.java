package ua.bestlunch.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.bestlunch.model.User;
import ua.bestlunch.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Виктор on 27.11.2016.
 */
@RestController
@RequestMapping(value = AdminRestController.REST_URL)
public class AdminRestController {

    static final String REST_URL = "/rest/admin/users";

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id){
        return service.get(id);
    }

    @RequestMapping(value = "/by", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam("email") String email){
        return service.getByEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        User created = service.save(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("rest/admin/users/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);

    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user){
        service.update(user);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }
}
