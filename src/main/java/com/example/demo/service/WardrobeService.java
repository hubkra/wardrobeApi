package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Wardrobe;
import com.example.demo.repo.WardrobeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WardrobeService {
    private final WardrobeRepo wardrobeRepo;

    @Autowired
    public WardrobeService(WardrobeRepo wardrobeRepo) {

        this.wardrobeRepo = wardrobeRepo;
    }

    public Wardrobe addClothes(Wardrobe wardrobe)
    {
        return wardrobeRepo.save(wardrobe);

    }

    public List<Wardrobe> findClothes()
    {
        return wardrobeRepo.findAll();
    }

    public Wardrobe updateClothes(Wardrobe wardrobe)
    {
        return wardrobeRepo.save(wardrobe);
    }

    public Wardrobe findClothesById(Long id)
    {
        return wardrobeRepo.findClothesById(id).orElseThrow(() -> new UserNotFoundException("Ubranie o id "+id + " nie zostało odnaleziony"));
    }
    public void deleteClothes(Long id){
        wardrobeRepo.deleteById(id);
    }
}
