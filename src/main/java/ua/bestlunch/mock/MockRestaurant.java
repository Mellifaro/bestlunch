package ua.bestlunch.mock;

import ua.bestlunch.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виктор on 13.08.2016.
 */
public class MockRestaurant {

    public static List<Restaurant> getAll(){
        List<Restaurant> restaurants = new ArrayList<>();
//        restaurants.add(new Restaurant(1, "Versal", "Franka, 27", 1200));
//        restaurants.add(new Restaurant(2, "Marsel", "Shevchenka, 35", 1500));
//        restaurants.add(new Restaurant(3, "Lion",   "Soborna, 41", 1218));
//        restaurants.add(new Restaurant(4, "Velur",  "Boguna, 54", 900));
        return restaurants;
    }
}
