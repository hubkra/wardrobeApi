package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Outfit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "outfit_wardrobe",
            joinColumns = @JoinColumn(name = "outfit_id"),
            inverseJoinColumns = @JoinColumn(name = "wardrobe_id")
    )
    private List<Wardrobe> wardrobeItems;

    @Column
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Outfit() {
        this.wardrobeItems = new ArrayList<>();
    }

    public Outfit(Long id) {
        this.id = id;
        this.wardrobeItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Wardrobe> getWardrobeItems() {
        return wardrobeItems;
    }

    public void setWardrobeItems(List<Wardrobe> wardrobeItems) {
        this.wardrobeItems = wardrobeItems;
    }

    public void addWardrobeItem(Wardrobe wardrobeItem) {
        if (wardrobeItems.size() < 5) {
            wardrobeItems.add(wardrobeItem);
        } else {
            throw new IllegalStateException("Maximum number of wardrobe items exceeded for this outfit.");
        }
    }

    public void removeWardrobeItem(Wardrobe wardrobeItem) {
        wardrobeItems.remove(wardrobeItem);
    }
}
