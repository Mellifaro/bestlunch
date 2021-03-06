package ua.bestlunch.model.to;

import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Restaurant;

import java.io.Serializable;

/**
 * Created by Виктор on 23.08.2016.
 */
public class RestaurantWithLunch implements Serializable {
    private static final long serialVersionUID = -1414551519149331638L;

    private int id;
    private String name;
    private String address;
    private String phone;
    private Lunch lunch;
    private int votes;

    public RestaurantWithLunch() {
    }

    public RestaurantWithLunch(Restaurant restaurant, Lunch lunch, int votes){
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.phone = restaurant.getPhone();
        this.lunch = lunch;
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Lunch getLunch() {
        return lunch;
    }

    public String getAddress() {
        return address;
    }

    public int getVotes() {
        return votes;
    }

    public String getPhone() {
        return phone;
    }
}
