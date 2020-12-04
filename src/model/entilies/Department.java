package model.entilies;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jonas create 03/12/2020
 */
public class Department implements Serializable {

    private static final long serialVersion = 1l;

    private Integer id;
    private String name;

    //COSNTRUTOR VAZIO
    public Department() {
    }

    //COSNTRUTOR SOBRECARREGADO
    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //HASHCODE
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.name);
        return hash;
    }

    //EQUALS
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + '}';
    }

    //GET AND SET
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

}
