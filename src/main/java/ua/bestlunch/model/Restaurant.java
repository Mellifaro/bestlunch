package ua.bestlunch.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Виктор on 13.08.2016.
 */
@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{

    public static final String ALL_SORTED = "User.getAllSorted";

    @Column(name = "address")
    @Length(min = 5)
    @NotEmpty()
    private String address;

    public Restaurant(){
    }

    public Restaurant(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
