package com.example.demo.service;

import com.example.demo.exception.OutfitNotFoundException;
import com.example.demo.model.Outfit;
import com.example.demo.repo.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutfitService {
    private final OutfitRepository outfitRepository;

    @Autowired
    public OutfitService(OutfitRepository outfitRepository) {
        this.outfitRepository = outfitRepository;
    }

    public Outfit createOutfit(Outfit outfit) {
        return outfitRepository.save(outfit);
    }

    public List<Outfit> getAllOutfits() {
        return outfitRepository.findAll();
    }

    public Outfit getOutfitById(Long id) {
        return outfitRepository.findById(id)
                .orElseThrow(() -> new OutfitNotFoundException("Outfit with ID " + id + " not found."));
    }

    public Outfit updateOutfit(Outfit outfit) {
        return outfitRepository.save(outfit);
    }

    public void deleteOutfit(Long id) {
        outfitRepository.deleteById(id);
    }
}
