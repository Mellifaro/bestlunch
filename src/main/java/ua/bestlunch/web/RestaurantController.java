package ua.bestlunch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.bestlunch.service.restaurant.RestaurantService;

/**
 * Created by Виктор on 02.09.2016.
 */
@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String getAll(ModelMap model){
        model.addAttribute("restaurants", service.getAll());
        return "restaurants";
    }
}
