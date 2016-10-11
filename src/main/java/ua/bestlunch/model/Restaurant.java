package ua.bestlunch.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;


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

    @Column(name = "phone")
    @Length(min = 6, max = 12)
    private String phone;

    public Restaurant(){
    }

    public Restaurant(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
