package ua.bestlunch.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.bestlunch.model.Role;
import ua.bestlunch.model.User;
import ua.bestlunch.service.UserService;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by Виктор on 11.12.2016.
 */
@RestController
@RequestMapping(value = "ajax/admin/users")
public class AdminAjaxController {

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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable ("id") int id){
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@RequestParam("id") Integer id,
                       @RequestParam("name") String name,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password){
        User user = new User(id, name, email, password, EnumSet.of(Role.ROLE_USER));
        if(user.isNew()){
            service.save(user);
        }else{
            service.update(user);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled){
        service.enable(id, enabled);
    }
}
