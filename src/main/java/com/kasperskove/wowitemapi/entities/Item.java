package com.kasperskove.wowitemapi.entities;

import javax.persistence.*;

@Entity
@Table (name = "items")
public class Item {

    @Id
    int id;

    @Column
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
