package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public User  loginUser(@RequestBody User user) throws Exception {
        String tempEmailId =user.getEmailId();
        String tempPassword = user.getPassword();
        User userObj =null;
        if(tempEmailId !=null && tempPassword !=null)
        {
           userObj=  service.fetchUserByEmailIdAndPassword(tempEmailId, tempPassword);
        }
        if(userObj == null){
            throw new Exception("Niezgodne dane!");
        }
        return userObj;
    }
}
