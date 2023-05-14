package com.example.demo.repo;

import com.example.demo.model.Wardrobe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface WardrobeRepo extends JpaRepository<Wardrobe,Long> {

    
    Optional<Wardrobe> findClothesById(Long id);

    void deleteById(Long id);
}
