package ua.bestlunch.service.restaurant.withlunch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.to.RestaurantWithLunch;
import ua.bestlunch.service.restaurant.RestaurantService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Виктор on 23.08.2016.
 */
//@Service
//public class RestaurantWthLunchService {
//
//    @Autowired
//    private RestaurantService restService;
//
//    @Autowired
//    private LunchService lunchService;
//
//    public List<RestaurantWithLunch> getRestaurantsWithLunch(){
//        List<Restaurant> restaurants = restService.getAll();
//        return restaurants.stream()
//                .map(restaurant -> convertEntityToDTO(restaurant))
//                .collect(Collectors.toList());
//    }
//
//    private RestaurantWithLunch convertEntityToDTO(Restaurant restaurant){
//        return new RestaurantWithLunch(restaurant, lunchService.getLunchByRestaurant);
//    }
//
//}
