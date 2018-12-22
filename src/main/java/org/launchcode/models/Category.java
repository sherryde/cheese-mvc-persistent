package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

// https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html
@Entity
public class Category {

    @javax.persistence.Id
    @GeneratedValue
    private int Id;

    @NotNull
    @Size(min = 3, max = 21)
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Cheese> cheeses = new ArrayList<>();


    public Category(String name) {
        this.name = name;
    }

    public Category() { }

    public int getId(){ return Id; }
    //    public void setId(int id) { Id = id; }

    public String getName(){ return name; }
    public void setName(String name){
        this.name = name;
    }

    public List<Cheese> getCheeses() { return cheeses; }
}



// Index of Annotations: https://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html#IndexOfAnnotations