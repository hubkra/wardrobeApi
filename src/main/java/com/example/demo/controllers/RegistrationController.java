package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {
    @Autowired
    private RegistrationService service;
    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user) throws Exception {
        String tempEmailId = user.getEmailId();
        User userObj =null;
        if(tempEmailId !=null && !"".equals(tempEmailId))
        {
            User userobj = service.fetchUserByEmailId(tempEmailId);
            if(userobj !=null)
            {
                throw new Exception("Uzytkownik z takim emailem "+tempEmailId+" juz istnieje");
            }
            userObj= service.saveUser(user);

        }
        if(userObj == null){
            throw new Exception("Niezgodne dane!");
        }
        return userObj;
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        try {
            String tempEmailId = user.getEmailId();
            String tempPassword = user.getPassword();

            User userObj = service.fetchUserByEmailId(tempEmailId);

            if (userObj != null && userObj.checkPassword(tempPassword)) {
                return ResponseEntity.status(HttpStatus.OK).body("Zalogowano pomyślnie.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Błędne dane logowania.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas logowania.");
        }
    }
}
