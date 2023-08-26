package com.example.demo.dto;

import com.example.demo.model.Wardrobe;

import java.util.List;

public class CreateOutfitRequest {
    private List<Wardrobe> wardrobeItems;
    private String name;

    public List<Wardrobe> getWardrobeItems() {
        return wardrobeItems;
    }

    public void setWardrobeItems(List<Wardrobe> wardrobeItems) {
        this.wardrobeItems = wardrobeItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
