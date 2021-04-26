package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Exception.AppException;
import com.example.Ecoleback.Model.*;
import com.example.Ecoleback.Repository.*;
import com.example.Ecoleback.Service.IPushNotifService;
import com.example.Ecoleback.Service.ItimeTableService;
import com.example.Ecoleback.Util.AdminU;
import com.example.Ecoleback.Util.ParentU;
import com.example.Ecoleback.Util.ProfU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
LevelRepository levelRepository;

    @Autowired
    AdminRepository adminRepository ;
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    UserRepository userRepository ;
    @Autowired
    SonRepository sonRepository ;
    @Autowired
    CodeRepository codeRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    IPushNotifService notifService;
    @Autowired
    ProfRepository profRepository;
    @Autowired
    ItimeTableService tableService;
    @Autowired
    TimeTableRepository tableRepository;
    @RequestMapping(value = "/add/parent",method = RequestMethod.POST)

    public Parent addParent (@RequestBody ParentU parentu){
        Parent parent=new Parent() ;
        parent.setPhoneNum(parentu.getPhoneNum());
        parent.setEmail(parentu.getEmail());
        parent.setFirstName(parentu.getFirstName());
        parent.setLastName(parentu.getLastName());
         parent.setPassword(passwordEncoder.encode(parentu.getPassword()));
        parent.setRole(Role.PARENT);
        Optional<Son>son=sonRepository.findById(parentu.getMatStud());
        Optional<Code> codeOptional=codeRepository.findCodeBySonId(parentu.getMatStud());
        Long x=codeOptional.get().getCode();
        Long y=Long.valueOf(parentu.getCode());

        if (x.equals(y)==false){

            throw new AppException("code Not exist","99999");


        }


        if (son.isEmpty()){
            throw new AppException("student Not exist","99");
        }else{
           parent= userRepository.save(parent);

            Son son1= son.get();
            son1.setUser(parent);
            sonRepository.save(son1);


        }


        return  parent;





    }
    @RequestMapping(value = "/add/admin",method = RequestMethod.POST)

    public Admin addAdmin (@RequestBody AdminU adminU) {
        Admin admin = new Admin();

        admin.setPhoneNum(adminU.getPhoneNum());
        admin.setEmail(adminU.getEmail());
        admin.setFirstName(adminU.getFirstName());
        admin.setLastName(adminU.getLastName());
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordEncoder.encode(adminU.getPassword()));
        userRepository.save(admin);

        return  admin ;
    }
    @RequestMapping(value = "/add/son/parent",method = RequestMethod.PUT)
    public boolean addByParent (@RequestParam Long idParent,@RequestParam String idStudent){
        boolean v = false;
        Son son=null ;
        User user =null ;
        Optional<Son>son1=sonRepository.findById(idStudent);
        if (son1.isEmpty()){

            throw new AppException("student Not exist","99");
        }
      if(son1.isPresent()){
            Optional<User>user1=userRepository.findById(idParent);
            user=user1.get();
            son=son1.get() ;
            son.setUser(user);
            v=true ;




        }
          sonRepository.save(son);
       return  v ;

    }
    @RequestMapping(value = "/add/prof",method = RequestMethod.POST)
    public Prof addProf(@RequestBody ProfU profU){


        Prof prof=new Prof();
        Set<Subject> subjects=new HashSet<>();
        for (int i=0;i<profU.getSubjectId().size();i++){
            Optional<Subject>subjectOptional=subjectRepository.findById(profU.getSubjectId().get(i));
            subjects.add(subjectOptional.get());


        }
        Set<Level> levels=new HashSet<>();
        for (int i=0;i<profU.getLevelId().size();i++){
            Optional<Level>levelOptional=levelRepository.findById(profU.getLevelId().get(i));
            levels.add(levelOptional.get());


        }
        prof.setLevel(levels);

        int x=subjects.size();
        prof.setSubject(subjects);

        prof.setTimeTable(null);
        prof.setEmail(profU.getEmail());
        prof.setPassword(passwordEncoder.encode("Epi2020"));
        prof.setPhoneNum(profU.getPhoneNum());
        prof.setImageUser(null);
        prof.setRole(Role.TEACHER);
        prof.setFirstName(profU.getFirstName());
        prof.setLastName(profU.getLastName());

        userRepository.save(prof);

        return prof ;

    }
    @RequestMapping(value = "/add/device",method = RequestMethod.POST)
        public User addDevice(@RequestParam String idUser,@RequestParam String keyDevice){
        Device device=new Device();
        device.setKey(keyDevice);
        deviceRepository.save(device);
        Optional<User>userOptional=userRepository.findById(Long.valueOf(idUser));
        User user=userOptional.get();
       user.setDevice(device);
        return userRepository.save(user);



        }
    @RequestMapping(value = "/send/push/user",method = RequestMethod.POST)
    public void sendPushToparent(@RequestParam String msg,@RequestParam String idStud){
        notifService.sendNotifToUser(msg,idStud);

    }
    @RequestMapping(value = "/get/all/prof",method = RequestMethod.GET)
    public List<Prof>allProfs(){

        return profRepository.findAll();
    }
    @RequestMapping(value = "/get/sub/prof",method = RequestMethod.GET)
    public List<Prof>allProfSub(@RequestParam Long idSub){

        return profRepository.findAllBySubjectId(idSub);
    }
    @RequestMapping(value = "/add/table/prof",method = RequestMethod.POST)

    public Prof addTtable(@RequestParam ("file") MultipartFile file, @RequestParam String idProf, @RequestParam String title, @RequestParam String desc){
        return tableService.addTtableToUser(file,idProf,title,desc);

    }
    @RequestMapping(value = "/delete/table/prof/{idProf}",method = RequestMethod.DELETE)

    public void deleteTable(@PathVariable Long idProf){


        Optional<Prof>profOptional=profRepository.findById(idProf);
        Prof prof=profOptional.get();

        Long Idtable=prof.getTimeTable().getId();

        prof.setTimeTable(null);

        profRepository.save(prof);
        tableRepository.deleteById(Idtable);

    }


    @RequestMapping(value = "/get/prof/{levelId}",method = RequestMethod.GET)
    public List<Prof>allProfBylevel(@PathVariable Long levelId){
        return profRepository.findAllByLevelId(levelId);


}
    @RequestMapping(value = "/get/user/{id}",method = RequestMethod.GET)
    public  User findUser(@PathVariable Long id){
       Optional<User>userOptional=  userRepository.findById(id);
       User user=userOptional.get();
       return  user ;

    }










}
