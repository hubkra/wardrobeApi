package com.example.demo.controllers;

import com.example.demo.dto.CreateOutfitRequest;
import com.example.demo.model.Outfit;
import com.example.demo.model.Wardrobe;
import com.example.demo.service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/outfits")
public class OutfitController {
    private final OutfitService outfitService;

    @Autowired
    public OutfitController(OutfitService outfitService) {
        this.outfitService = outfitService;
    }

    @PostMapping
    public ResponseEntity<String> createOutfit(@RequestBody CreateOutfitRequest request) {
        List<Wardrobe> wardrobeItems = request.getWardrobeItems();
        String name = request.getName();
        if (wardrobeItems == null || wardrobeItems.isEmpty()) {
            return ResponseEntity.badRequest().body("No wardrobe items provided.");
        }

        System.out.println("Received wardrobeItems: " + wardrobeItems);

        // Create the outfit using the wardrobe items
        Outfit newOutfit = new Outfit();
        for (Wardrobe wardrobeItem : wardrobeItems) {
            newOutfit.addWardrobeItem(wardrobeItem);
        }

        System.out.println("Created newOutfit: " + newOutfit);

        outfitService.createOutfit(newOutfit);

        return ResponseEntity.ok("Stworzono nowy strój.");
    }

    @GetMapping
    public ResponseEntity<List<Outfit>> getAllOutfits() {
        List<Outfit> outfits = outfitService.getAllOutfits();
        return new ResponseEntity<>(outfits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Outfit> getOutfitById(@PathVariable Long id) {
        Outfit outfit = outfitService.getOutfitById(id);
        return new ResponseEntity<>(outfit, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Outfit> updateOutfit(@PathVariable Long id, @RequestBody Outfit outfit) {
        if (!id.equals(outfit.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Outfit updatedOutfit = outfitService.updateOutfit(outfit);
        return new ResponseEntity<>(updatedOutfit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutfit(@PathVariable Long id) {
        outfitService.deleteOutfit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}