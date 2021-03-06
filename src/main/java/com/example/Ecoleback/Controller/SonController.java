package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.Level;
import com.example.Ecoleback.Model.Parent;
import com.example.Ecoleback.Model.Son;
import com.example.Ecoleback.Model.imageUser;
import com.example.Ecoleback.Repository.ImageRepository;
import com.example.Ecoleback.Repository.LevelRepository;
import com.example.Ecoleback.Repository.ParentRepository;
import com.example.Ecoleback.Repository.SonRepository;
import com.example.Ecoleback.Service.ISonService;
import com.example.Ecoleback.Util.SonU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class SonController {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ISonService sonService ;
    @Autowired
    SonRepository sonRepository ;
    @Autowired
    LevelRepository levelRepository ;
    @Autowired
    ParentRepository parentRepository;
    @RequestMapping(value = "add/student",method = RequestMethod.POST)
    public Son addSon(@RequestBody SonU sonU){
       return sonService.addSon(sonU) ;
    }
    @RequestMapping(value = "students/level",method = RequestMethod.GET)
    public List<Son>allStydent(@RequestParam Long levelId){
        Optional<Level> level=levelRepository.findById(levelId);

       List<Son> sons= sonRepository.findAllByLevel(level.get());
      return sons ;
    }
    @RequestMapping(value = "delete/student/{id}",method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable String id){

        sonService.deletStud(id);
    }
    @RequestMapping(value = "students/level/{idStud}",method = RequestMethod.GET)
    public Long levelIdstud(@PathVariable String idStud){
        return sonService.levelId(idStud);
    }
    @RequestMapping(value = "update/student/{idStud}",method = RequestMethod.POST)
    public Son updStudent (@PathVariable String idStud,@RequestBody SonU sonU){
        return sonService.updStud(idStud,sonU);
    }
    @RequestMapping(value = "student/{idStud}",method = RequestMethod.GET)
    public Son getStudent (@PathVariable String idStud) {
       Optional<Son>sonOptional=sonRepository.findById(idStud);
       Son son=sonOptional.get();
       return son ;

    }
    @RequestMapping(value = "stud/Verf/{idStud}",method = RequestMethod.GET)
    public Boolean verfStudent (@PathVariable String idStud) {
        Boolean x=false;
        Optional<Son>sonOptional=sonRepository.findById(idStud);
        if(sonOptional.isPresent()){
            x=true;
        }
        if (!sonOptional.isPresent()){
            x=false;

        }
        return x ;

    }
    @RequestMapping(value = "stud/all",method = RequestMethod.GET)
    public List<Son>allStud(){
        return sonRepository.findAll();
    }
    @RequestMapping(value = "stud/all/{id}",method = RequestMethod.GET)
    public List<Son>allSonbyParent(@PathVariable Long id){
        return sonService.allSonbyParent(id);


    }
    @RequestMapping(value = "stud/img",method = RequestMethod.POST)
    public Son addImg(@RequestParam MultipartFile img,@RequestParam String id) throws IOException {

        Optional<Son>sonOptional=sonRepository.findById(id);
        Son son=sonOptional.get();
        imageUser imageUser=new  imageUser();
        imageUser.setPicByte(img.getBytes());
        imageUser.setName(img.getOriginalFilename());
        imageUser.setType(img.getContentType());
        imageRepository.save(imageUser);
        son.setImageUser(imageUser);
        return sonRepository.save(son);

    }
    @RequestMapping(value = "stud/parent",method = RequestMethod.POST)
    public Son addSon(@RequestParam String userId,String studId){
        Optional<Son>sonOptional=sonRepository.findById(studId);
        Son son =sonOptional.get();
        Optional<Parent>parentOptional=parentRepository.findById(Long.valueOf(userId));
        son.setUser(parentOptional.get());
      return   sonRepository.save(son);
    }














}
