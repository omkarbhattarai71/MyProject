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
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        Credential credential = new Credential();
        credential.setUsername(username);
        credential.setPassword(password);
        credentialRepository.save(credential);
        session.setAttribute("username", username);
        return  "usertypelink";

    }
    @PostMapping("/userdetails")
    public String userDetails(@RequestParam("fname") String fname,
                              @RequestParam("lname") String lname,
                              @RequestParam("email") String email,
                              @RequestParam("phone") String phone, HttpSession session, Model model)
    {
        Userdetail udetail = new Userdetail();
        udetail.setUsername((String) session.getAttribute("username"));
//        udetail.setUsername(username);
        udetail.setFname(fname);
        udetail.setEmail(email);
        udetail.setPhone(phone);
        udetail.setLname(lname);
        userdetailRepository.save(udetail);
        session.setAttribute("fname", fname);
        session.setAttribute("lname", lname);
        session.setAttribute("email", email);
        session.setAttribute("phone", phone);
        String username = (String) session.getAttribute("username");
        Optional<Userdetail> userdetail = userdetailRepository.findById(username);
        List<Usertypelink> usertypelinks = usertypelinkRepository.findAll();
        Optional<Usertypelink> usertypelink = usertypelinks.stream().filter(usertypelink1 -> usertypelink1.getUsername().equals(username)).findAny();

        if(userdetail.isPresent()){
            model.addAttribute("usedetail",userdetail.get());
            if(usertypelink.isPresent() && usertypelink.get().getType().equals("buyer")){
                return "buyerdashboard";
            }
            else if(usertypelink.isPresent() && usertypelink.get().getType().equals("seller")){
                return "sellerdashboard";
            }
            else{
                return "interimdashboard";
            }
        }
        else{
            return "interimdashboard";
        }
    }

    @PostMapping("/usertypelinks")
    public String usertypeLinks(@RequestParam("type") String type, HttpSession session){
        Usertypelink utypelink = new Usertypelink();
        utypelink.setId(String.valueOf(((int)(Math.random()*1000))));
//        session.setAttribute("username", username);
        utypelink.setUsername((String) session.getAttribute("username"));
//        utypelink.setUsername(username);
        utypelink.setType(type);
        usertypelinkRepository.save(utypelink);
        session.setAttribute("type",type);
        return "interimdashboard";
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
                    model.addAttribute("usedetail",userdetail.get());
                    if(usertypelink.isPresent() && usertypelink.get().getType().equals("buyer")){
                        return "buyerdashboard";
                    }
                    else if(usertypelink.isPresent() && usertypelink.get().getType().equals("seller")){
                        return "sellerdashboard";
                    }
                    else{
                        return "interimdashboard";
                    }
                }
//                userdetail.ifPresent(value -> model.addAttribute("userdetail", value));
                else{
                    return "interimdashboard";
                }
            }
            else{
                return "landingpage";
            }
        }
        else{
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
