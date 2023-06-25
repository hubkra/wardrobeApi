package com.example.demo.repo;

import com.example.demo.model.Outfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutfitRepository extends JpaRepository<Outfit,Long> {
}
