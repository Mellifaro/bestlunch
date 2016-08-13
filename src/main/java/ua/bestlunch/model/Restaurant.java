package ua.bestlunch.model;

/**
 * Created by Виктор on 13.08.2016.
 */
public class Restaurant {

    private int id;
    private String name;
    private String address;
    private int popularity;

    public Restaurant(int id, String name, String address, int popularity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
