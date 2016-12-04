package ua.bestlunch.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.bestlunch.model.User;
import ua.bestlunch.model.to.UserTo;
import ua.bestlunch.service.UserService;

import javax.validation.Valid;

/**
 * Created by Виктор on 27.11.2016.
 */
@RestController
@RequestMapping(value = "rest/profile")
public class UserRestController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id){
//        return service.get(AuthorizedUser.id());
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo){
//        userTo.setId(AuthorizedUser.id());
//        service.update(userTo);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(){
//        service.delete(AuthorizedUser.id());
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String testUTF(){
        return "Український текст";
    }
}
