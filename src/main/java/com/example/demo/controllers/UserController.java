package com.example.demo.controllers;


import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam String userEmailId, @RequestParam("file") MultipartFile file) {
        try {
            User user = userRepository.findByEmailId(userEmailId);

            if (user != null) {
                byte[] pictureBytes = file.getBytes();
                user.setProfilePicture(pictureBytes);
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Zdjęcie użytkownika zostało zaktualizowane.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Użytkownik o podanym adresie e-mail nie istnieje.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas przesyłania zdjęcia.");
        }
    }
}