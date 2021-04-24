package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.Subject;
import com.example.Ecoleback.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;
    @RequestMapping(value = "/add/subject",method = RequestMethod.POST)
    public Subject addSub(@RequestParam String name){

        Subject subject=new Subject();
        subject.setName(name);
        return subjectRepository.save(subject);
    }
    @RequestMapping(value = "/all/subject",method = RequestMethod.GET)
    public List<Subject>allSub(){
        return subjectRepository.findAll();
    }

}
