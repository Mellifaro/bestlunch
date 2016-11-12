package ua.bestlunch;

import ua.bestlunch.model.Dish;

import java.math.BigDecimal;

/**
 * Created by Виктор on 01.11.2016.
 */
public class DishTestData {

    public static final Dish DISH1 = new Dish(100, "Eggs", new BigDecimal(15.25), RestaurantTestData.RESTAURANT1);
    public static final Dish DISH2 = new Dish(101, "Tea", new BigDecimal(23.13), RestaurantTestData.RESTAURANT1);
    public static final Dish DISH3 = new Dish(102, "Soup", new BigDecimal(9.25), RestaurantTestData.RESTAURANT2);
    public static final Dish DISH4 = new Dish(103, "Bread", new BigDecimal(5.35), RestaurantTestData.RESTAURANT2);
    public static final Dish DISH5 = new Dish(104, "Cake", new BigDecimal(9.25), RestaurantTestData.RESTAURANT3);
    public static final Dish DISH6 = new Dish(105, "Ice-cream", new BigDecimal(3.50), RestaurantTestData.RESTAURANT3);
    public static final Dish DISH7 = new Dish(106, "Porridge", new BigDecimal(11.25), RestaurantTestData.RESTAURANT4);
    public static final Dish DISH8 = new Dish(107, "Pasta", new BigDecimal(10.00), RestaurantTestData.RESTAURANT4);

    public static Dish getCreated(){
        return new Dish(null, "Potatoes with mushrooms", new BigDecimal(13.00), RestaurantTestData.RESTAURANT1);
    }

    public static Dish getUpdated(){
        return new Dish(100, "Jack Daniels", new BigDecimal(30.00), RestaurantTestData.RESTAURANT1);
    }
}
