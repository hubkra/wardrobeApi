package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import com.example.demo.enums.SizeInformation;
@Entity
public class Wardrobe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String typeClothes;
    private String imageUrl;
    private SizeInformation size;




    public Wardrobe(){}

    public Wardrobe(Long id, String name, String typeClothes, String imageUrl, SizeInformation size) {
        this.id = id;
        this.name = name;
        this.typeClothes = typeClothes;
        this.imageUrl = imageUrl;
        this.size = size;


    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeClothes() {
        return typeClothes;
    }

    public void setTypeClothes(String typeClothes) {
        this.typeClothes = typeClothes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SizeInformation getSize() {
        return size;
    }

    public void setSize(SizeInformation size) {
        this.size = size;
    }
}



