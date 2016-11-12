package ua.bestlunch;

import ua.bestlunch.model.Lunch;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static java.time.LocalDateTime.*;

/**
 * Created by Виктор on 01.11.2016.
 */
public class LunchTestData {

    public static final Lunch LUNCH1 = new Lunch(100, "Breakfast1", new BigDecimal(15.25), of(2016, Month.NOVEMBER, 1, 10, 0), RestaurantTestData.RESTAURANT1);
    public static final Lunch LUNCH2 = new Lunch(101, "Breakfast2", new BigDecimal(18.35), of(2016, Month.NOVEMBER, 1, 11, 0), RestaurantTestData.RESTAURANT1);
    public static final Lunch LUNCH3 = new Lunch(102, "Dinner1'", new BigDecimal(9.25), of(2016, Month.NOVEMBER, 1, 12, 0), RestaurantTestData.RESTAURANT1);
    public static final Lunch LUNCH4 = new Lunch(103, "Dinner2", new BigDecimal(10.00), of(2016, Month.NOVEMBER, 1, 13, 0), RestaurantTestData.RESTAURANT2);

    public static Lunch getCreated(){
        return new Lunch(null, "Supper1", new BigDecimal(30.00), of(2016, Month.NOVEMBER, 1, 18, 0), RestaurantTestData.RESTAURANT2);
    }

    public static Lunch getUpdated(){
        return new Lunch(100, "Breakfast10", new BigDecimal(5.00), of(2016, Month.APRIL, 10, 10, 0), RestaurantTestData.RESTAURANT1);
    }
}
