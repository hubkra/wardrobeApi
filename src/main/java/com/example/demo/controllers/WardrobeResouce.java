package com.example.demo.controllers;

import com.example.demo.model.Wardrobe;
import com.example.demo.service.WardrobeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/wardrobe")
public class WardrobeResouce {
    private final WardrobeService wardrobeService;

    public WardrobeResouce(WardrobeService wardrobeService) {
        this.wardrobeService = wardrobeService;
    }



    @GetMapping("/all")
    public ResponseEntity<List<Wardrobe>> getAllClothes ()
    {
        List<Wardrobe> wardrobes = wardrobeService.findClothes();
        return new ResponseEntity<>(wardrobes, HttpStatus.OK);
    }



    @GetMapping("/find/{id}")
    public ResponseEntity<Wardrobe> getClothesById(@PathVariable("id") Long id)
    {
        Wardrobe wardrobe = wardrobeService.findClothesById(id);
        return new ResponseEntity<>(wardrobe, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Wardrobe> addClothes(@RequestBody Wardrobe wardrobe)
    {
        Wardrobe newWardrobe = wardrobeService.addClothes(wardrobe);
        return new ResponseEntity<>(newWardrobe, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Wardrobe> updateClothes(@RequestBody Wardrobe wardrobe) {
        Wardrobe updateWardrobe = wardrobeService.addClothes(wardrobe);
        return new ResponseEntity<>(updateWardrobe, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClothes(@PathVariable("id") Long id) {
        wardrobeService.deleteClothes(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
it