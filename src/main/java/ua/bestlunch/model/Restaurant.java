package ua.bestlunch.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Set;


@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT DISTINCT r FROM Restaurant r ORDER BY r.name")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";

    @Column(name = "address")
    @Length(min = 5)
    @NotEmpty()
    private String address;

    @Column(name = "phone")
    @Length(min = 6, max = 16)
    private String phone;

    public Restaurant(){
    }

    public Restaurant(Integer id, String name, String address, String phone) {
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + super.id + '\'' +
                ", name='" + super.name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        if (!super.equals(o)) return false;

        Restaurant that = (Restaurant) o;

        if (!address.equals(that.address)) return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
