package ua.bestlunch.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Виктор on 13.08.2016.
 */
@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.popularity")
})
@Entity
@Table(name = "restautants")
public class Restaurant extends NamedEntity{

    public static final String ALL_SORTED = "User.getAllSorted";

    @Column(name = "address")
    @Length(min = 5)
    @NotEmpty()
    private String address;

    @Column(name = "popularity")
    @NotEmpty
    private int popularity;

    public Restaurant(){
    }

    public Restaurant(int id, String name, String address, int popularity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.popularity = popularity;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
