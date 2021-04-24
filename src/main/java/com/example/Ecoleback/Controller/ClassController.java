package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.Level;
import com.example.Ecoleback.Repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)


public class ClassController {
    public static Integer student=0 ;

    @Autowired
    LevelRepository levelRepository ;
    @RequestMapping(path = "add/class",method = RequestMethod.POST)
    public Level addClass(@RequestParam String level){
        Level level1=new Level() ;
       level1.setName(level);
       level1.setNumStud(student);
       return levelRepository.save(level1);

    }
    @RequestMapping(path = "all/class",method = RequestMethod.GET)

    public List<Level> allClass(){
     List<Level>x= levelRepository.findAll();
     return x ;

    }
    @RequestMapping(path = "get/level/{id}",method = RequestMethod.GET)
    public Optional<Level> getLevel(@PathVariable Long id){
        return levelRepository.findById(id);
    }



}
