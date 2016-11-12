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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedEntity)) return false;
        if (!super.equals(o)) return false;

        NamedEntity that = (NamedEntity) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
