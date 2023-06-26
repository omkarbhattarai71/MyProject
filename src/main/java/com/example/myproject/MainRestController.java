package com.example.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
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
}
