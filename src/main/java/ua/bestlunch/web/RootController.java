package ua.bestlunch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.service.RestaurantService;

/**
 * Created by Виктор on 11.12.2016.
 */
@Controller
public class RootController {

    @Autowired
    private RestaurantService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users() {
        return "users";
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
