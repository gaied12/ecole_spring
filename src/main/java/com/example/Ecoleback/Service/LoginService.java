package com.example.Ecoleback.Service;

import com.example.Ecoleback.Exception.AppException;
import com.example.Ecoleback.Model.Code;
import com.example.Ecoleback.Model.Son;
import com.example.Ecoleback.Model.User;
import com.example.Ecoleback.Repository.CodeRepository;
import com.example.Ecoleback.Repository.SonRepository;
import com.example.Ecoleback.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class LoginService  implements  ILoginService{
    @Autowired
    SonRepository sonRepository;
    @Autowired
    CodeRepository codeRepository;
    @Autowired
    MailService  mailService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Code addCode(String idStud, String email) throws Exception {
        Long key=(long) Math.floor(Math.random() * 899999 + 100000);
        Code code=null ;

        Optional<Code>codeOptional=codeRepository.findCodeBySonId(idStud);
        if (!codeOptional.isPresent()){
            code=new Code();
            code.setCode(key);
            Optional<Son>sonOptional=sonRepository.findById(idStud);
            code.setSon(sonOptional.get());
            code=codeRepository.save(code);


        }
        if (codeOptional.isPresent()){
            code=codeOptional.get();
            code.setCode(key);
            code=codeRepository.save(code);


        }
        mailService.sendEmail(email,String.valueOf(code.getCode()));
        return  code ;


    }

    @Override
    public User authenticated(String email, String password) throws Exception {
        Optional<User>userOptional=this.userRepository.findUserByEmail(email);

        if (!userOptional.isPresent() ){
            throw new AppException(" user or password incorect","98");
        }
        if (userOptional.isPresent()){

        }
        User user=userOptional.get();


        String passData=user.getPassword() ;

        boolean valid= passwordEncoder.matches(password,passData);

        if ( valid==false ){
            throw new AppException(" user or password incorect","98");
        }


        return user ;



    }

}
