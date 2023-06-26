package com.example.myproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController{

    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    UserdetailRepository userdetailRepository;

    @Autowired
    UsertypelinkRepository usertypelinkRepository;

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
    @PostMapping("/userdetails")
    public String userDetails(@RequestParam("username") String username,
                              @RequestParam("fname") String fname,
                              @RequestParam("lname") String lname,
                              @RequestParam("email") String email,
                              @RequestParam("phone") String phone, HttpSession session)
    {
        Userdetail udetail = new Userdetail();
        udetail.setUsername(username);
        udetail.setFname(fname);
        udetail.setEmail(email);
        udetail.setPhone(phone);
        udetail.setLname(lname);
        userdetailRepository.save(udetail);
        return "usertypelink";
    }

    @PostMapping("/usertypelinks")
    public String usertypeLinks(@RequestParam("id") String id,
                                @RequestParam("username") String username,
                                @RequestParam("type") String type, HttpSession session){
        Usertypelink utypelink = new Usertypelink();
        utypelink.setId(id);
        utypelink.setUsername(username);
        utypelink.setType(type);
        usertypelinkRepository.save(utypelink);
        return "usertypelink";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model){    // @RequestParam("type") String type
        Optional<Credential> matchedCredential = credentialRepository.findById(username);
        if(matchedCredential.isPresent()){
            if(matchedCredential.get().getPassword().equals(password)){
                session.setAttribute("username", username);
                Optional<Userdetail> userdetail = userdetailRepository.findById(username);
                List<Usertypelink> usertypelinks = usertypelinkRepository.findAll();
                Optional<Usertypelink> usertypelink = usertypelinks.stream().filter(usertypelink1 -> usertypelink1.getUsername().equals(username)).findAny();

                if(userdetail.isPresent()){
                    model.addAttribute("usedetail",userdetail);

                    if(usertypelink.get().getType().equals("BUYER")){
                        return "buyerdashboard";

                    }
                    else if(usertypelink.get().getType().equals("SELLER")){
                        return "sellerdashboard";
                    }
                    else{
                        return "interimdashboard";
                    }
                }
//                userdetail.ifPresent(value -> model.addAttribute("userdetail", value));

                return "interimdashboard";
            }
            else{
                return "landingpage";
            }
        }
        else {
            return "landingpage";
        }
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
