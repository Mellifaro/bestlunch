package ua.bestlunch.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Виктор on 16.08.2016.
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity{

    @NotEmpty
    @Column(name = "name", nullable = false)
    protected String name;

    public NamedEntity(){
    }

    public NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
