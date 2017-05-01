package ua.bestlunch.model.to;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Виктор on 12.11.2016.
 */
public class UserTo implements Serializable{
    private static final long serialVersionUID = 4360395719334854365L;

    private Integer id;

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    @Size(min = 5, max = 64, message = "must be between 5 and 64 characters")
    private String password;

    public UserTo(){}

    public UserTo(Integer id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean isNew(){
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
