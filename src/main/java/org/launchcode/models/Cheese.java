package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaunchCode
 */
//flag that tells spring boot to store this class in a database.
@Entity
public class Cheese {
// properties to be stored the database
/* adding an entity association: all of these fields should be stored in a table
that is a associated with this class Cheese.
*/
    // the id field should be an id ==> primary key: unique and a primary key column in the database
    // hibernate or data layer should generate that value and manage the value creation.
    @Id // a primary key id; unique
    @GeneratedValue // data layer will generate and manage
    private int id;

    @NotNull
    @Size(min=3, max=21) // drop db if changing size or nested SQL error
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus = new ArrayList<>();


    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // default constructor that hibernate will use
    public Cheese() { }

    // getter for the id (except when adding to another form)
    public int getId() {
        return id;
    } // no setter because it shouldn't be changed outside this class

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    //public List<Menu> getMenus() { return menus; }

}

