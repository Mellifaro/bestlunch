package ua.bestlunch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.bestlunch.service.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by Виктор on 02.09.2016.
 */

//delete
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private DishService dishService;

    @Autowired
    private LunchService lunchService;


    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    private String getAll(ModelMap model){
        model.addAttribute("restaurants", restaurantService.getAllWithLunches());
        return "restaurants";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    private String getAllUsers(ModelMap model){
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/bestlunch");
            Connection conn = ds.getConnection();
        }catch (Throwable e){
            e.printStackTrace();
        }
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping(value = "/votes/{idUser}", method = RequestMethod.GET)
    private String getAllVotes(ModelMap model, @PathVariable("idUser") int id){
        model.addAttribute("votes", voteService.findAllByUser(userService.get(id)));
        return "votes";
    }

    @RequestMapping(value = "/dishes/{idRestaurant}", method = RequestMethod.GET)
    private String getAllDishes(ModelMap model, @PathVariable("idRestaurant") int id){
        model.addAttribute("dishes", dishService.getAllByRestaurant(id));
        return "dishes";
    }

    @RequestMapping(value = "/lunches/{idRestaurant}", method = RequestMethod.GET)
    private String getAllLunches(ModelMap model, @PathVariable("idRestaurant") int id){
        model.addAttribute("lunches", lunchService.getAllByRestaurant(id));
        return "lunches";
    }
}
