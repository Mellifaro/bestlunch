package ua.bestlunch;

import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Restaurant;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Виктор on 31.10.2016.
 */
public class RestaurantTestData {

    public static final Restaurant RESTAURANT1 = new Restaurant(100, "Versal", "Franka, 27", "+380635254123");
    public static final Restaurant RESTAURANT2 = new Restaurant(101, "Marsel", "Shevchenka, 35", null);
    public static final Restaurant RESTAURANT3 = new Restaurant(102, "Lion", "Soborna, 41", "+380635254000");
    public static final Restaurant RESTAURANT4 = new Restaurant(103, "Velur", "Boguna, 54", "+380635254101");

    public static final List<Restaurant> RESTAURANT_LIST = Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4);

    public static Restaurant getCreated(){
        return new Restaurant(null, "Vinnitsa", "Shevchenka, 2", "+380969965932");
    }

    public static Restaurant getUpdated(){
        return new Restaurant(RESTAURANT1.getId(), RESTAURANT1.getName(), "Litvinenko, 2", "+380630000000");
    }
}
