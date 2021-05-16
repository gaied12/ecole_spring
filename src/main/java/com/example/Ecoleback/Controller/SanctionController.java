package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.File;
import com.example.Ecoleback.Model.Sanc;
import com.example.Ecoleback.Model.SancType;
import com.example.Ecoleback.Model.Sanction;
import com.example.Ecoleback.Repository.SancRepository;
import com.example.Ecoleback.Repository.SanctionRepository;
import com.example.Ecoleback.Service.ISanctionService;
import com.example.Ecoleback.Util.SanctionU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class SanctionController {
    @Autowired
    SancRepository sancRepository;
    @Autowired
    SanctionRepository sanctionRepository;
    @Autowired
    ISanctionService sanctionService;
    @PostConstruct
    public void sancType() {

        for (Sanc sanc : Sanc.values()) {

            SancType sancType=new SancType();
            sancType.setType(Sanc.values()[sanc.ordinal()]);
            Optional<SancType>sancTypeOptional=sancRepository.findByType(Sanc.values()[sanc.ordinal()]);
            if (!sancTypeOptional.isPresent()){
                sancRepository.save(sancType);


            }



        }
    }
    @RequestMapping(value = "/add/sanction",method = RequestMethod.POST)
    public Sanction addSanction(@RequestBody SanctionU sanctionU){
        return  sanctionService.addSanction(sanctionU);

    }
    @RequestMapping(value = "/all/sanctionType",method = RequestMethod.GET)
    public List<SancType> allSanctionType(){
        return  sancRepository.findAll();

    }
    @RequestMapping(value = "/all/sanction/stud/{id}",method = RequestMethod.GET)
    public  List <Sanction>allSancByStud(@PathVariable String id){
        return sanctionRepository.findAllBySonId(id);

    }




}
