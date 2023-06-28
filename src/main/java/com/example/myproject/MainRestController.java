package com.example.myproject;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("api/1.2")
public class MainRestController {
    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    UserdetailRepository userdetailRepository;

    @Autowired
    UsertypelinkRepository usertypelinkRepository;

//    public Optional<Userdetail> getUserdetailsByEmail(@RequestParam("email") String email){
//        if(userdetailRepository.findByEmail(email).isPresent()){
//            return userdetailRepository.findByEmail(email).get(),HttpStatus.OK);
//        }
//    }
//
    @PostMapping("getuserbyemail")
    public ResponseEntity<Userdetail> getUserdetailsByEmail(@RequestParam("email") String email){
        if(userdetailRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>(userdetailRepository.findByEmail(email).get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @CrossOrigin("http://localhost:4200/")
    @GetMapping("getdummyuser")
    public Userdetail getDummyUser(){
        Userdetail udetail = new Userdetail();
        udetail.setUsername("omkarbhattarai");
        udetail.setFname("Omkar");
        udetail.setLname("Bhattarai");
        udetail.setEmail("omkarbhattarai71@gmail.com");
        udetail.setPhone("8750086742");
        return udetail;
    }
}
