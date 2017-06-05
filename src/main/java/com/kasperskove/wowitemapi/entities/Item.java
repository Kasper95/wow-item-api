package com.kasperskove.wowitemapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    private String itemLevel;
    @Column
    private String armor;
    @Column
    private String inventoryType;

    @Column
    @OneToMany (cascade = CascadeType.ALL)
    private List<Stat> bonusStats;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(String itemLevel) {
        this.itemLevel = itemLevel;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public List<Stat> getBonusStats() {
        return bonusStats;
    }

    public void setBonusStats(List<Stat> bonusStats) {
        this.bonusStats = bonusStats;
    }
}
