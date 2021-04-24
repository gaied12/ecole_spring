package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.Code;
import com.example.Ecoleback.Model.User;
import com.example.Ecoleback.Service.LoginService;
import com.example.Ecoleback.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    MailService mailService;

    @RequestMapping(path = "send/code",method = RequestMethod.POST)
    public Code addCode(@RequestParam String idStud,@RequestParam(required = false) String email) throws Exception {
        return loginService.addCode(idStud,email);



    }
    @RequestMapping(path = "send/Email",method = RequestMethod.POST)
    public  void sendEmail(@RequestParam String to,String text){
        mailService.sendEmail(to, text);
    }
    @RequestMapping(path = "login",method = RequestMethod.POST)
    public User auth(@RequestParam String email, String password) throws Exception {
        return  loginService.authenticated(email, password);
    }




}
