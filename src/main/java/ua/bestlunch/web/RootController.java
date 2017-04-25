package ua.bestlunch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import ua.bestlunch.AuthorizedUser;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.to.UserTo;
import ua.bestlunch.service.RestaurantService;
import ua.bestlunch.service.UserService;

import javax.validation.Valid;

/**
 * Created by Виктор on 11.12.2016.
 */
@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message){
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model){
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //todo change method. It doesn't work
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                //todo change super.create
                //super.create(UserUtil.createNewFromTo(userTo));
                status.setComplete();
                return "redirect:login?message=app.registered";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "exception.duplicate_email");
            }
        }
        model.addAttribute("register", true);
        return "profile";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:restaurants";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users() {
        return "users";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile() {
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    //todo change method. It doesn't work
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            try {
                userTo.setId(AuthorizedUser.id());
                userService.update(userTo);
                AuthorizedUser.get().update(userTo);
                status.setComplete();
                return "redirect:restaurants";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "exception.duplicate_email");
            }
        }
        return "profile";
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String restaurants(){
        return "restaurants";
    }

    @RequestMapping(value = "/restaurants/{rest_id}/lunches", method = RequestMethod.GET)
    public String lunches(ModelMap map, @PathVariable("rest_id") int id){
        map.addAttribute("restaurant", service.get(id));
        return "lunches";
    }

    @RequestMapping(value = "/restaurants/{rest_id}/dishes", method = RequestMethod.GET)
    public String dishesByRestaurant(ModelMap map, @PathVariable("rest_id") int id){
        map.addAttribute("restaurant", service.get(id));
        return "dishes";
    }

    @RequestMapping(value = "/restaurants/{rest_id}/lunches/{lunch_id}/dishes", method = RequestMethod.GET)
    public String dishesByLunch(ModelMap map, @PathVariable("rest_id") int rest_id, @PathVariable("rest_id") int lunch_id){
        map.addAttribute("restaurant", service.get(rest_id));
        return "dishes";
    }

    @RequestMapping(value = "/profile/votes", method = RequestMethod.GET)
    public String votes(){
        return "votes";
    }
}
