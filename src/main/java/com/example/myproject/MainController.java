package com.example.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController{

    @Autowired
    CredentialRepository credentialRepository;

    @GetMapping("/")
    public String getLangingPage(){
        return "landingpage";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password){
        Credential credential = new Credential();
        credential.setUsername(username);
        credential.setPassword(password);
        credentialRepository.save(credential);
        return  "dashboard";

    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        Credential credential = new Credential();
        credential.getUsername();
        credential.getPassword();
        credentialRepository.save(credential);
        return  "dashboard";

    }
    @GetMapping("/save")
    public String saveCredential(){
        Credential cr = new Credential();
        cr.setUsername("Omkar");
        cr.setPassword("omkar@123");
        credentialRepository.save(cr);
        return "New Credential Saved";

    }


}
