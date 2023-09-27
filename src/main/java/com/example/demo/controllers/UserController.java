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

    @PutMapping("/update/{userEmailId}")
    public ResponseEntity<String> updateUser(
            @PathVariable String userEmailId,
            @RequestBody User updateUserRequest
    ) {
        try {
            User user = userRepository.findByEmailId(userEmailId);

            if (user != null) {
                if (updateUserRequest.getPassword() != null) {
                    user.setPassword(updateUserRequest.getPassword());
                }
                if (updateUserRequest.getEmailId() != null) {
                    user.setEmailId(updateUserRequest.getEmailId());
                }
                if (updateUserRequest.getUserName() != null) {
                    user.setUserName(updateUserRequest.getUserName());
                }

                userRepository.save(user);

                return ResponseEntity.status(HttpStatus.OK).body("Dane użytkownika zostały zaktualizowane.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Użytkownik o podanym adresie e-mail nie istnieje.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas aktualizacji danych użytkownika.");
        }
    }
    @GetMapping("/{userEmailId}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String userEmailId) {
        try {
            User user = userRepository.findByEmailId(userEmailId);

            if (user != null) {
                return ResponseEntity.status(HttpStatus.OK).body(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
