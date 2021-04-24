package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.Metting;
import com.example.Ecoleback.Model.Parent;
import com.example.Ecoleback.Model.Prof;
import com.example.Ecoleback.Model.Son;
import com.example.Ecoleback.Repository.MettingRepository;
import com.example.Ecoleback.Repository.ParentRepository;
import com.example.Ecoleback.Repository.ProfRepository;
import com.example.Ecoleback.Repository.SonRepository;
import com.example.Ecoleback.Util.MettingU;
import com.example.Ecoleback.Util.SonU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class MettingController {
    @Autowired
    MettingRepository mettingRepository;
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    SonRepository sonRepository;
    @Autowired
    ProfRepository profRepository;



    @RequestMapping(value = "add/metting",method = RequestMethod.POST)
    public Metting addMett(@RequestBody MettingU mettingU){
        Optional<Prof>profOptional=profRepository.findById(mettingU.getProfId());
        Optional<Parent>parentOptional=parentRepository.findById(mettingU.getParentId());
        Optional<Son>sonOptional=sonRepository.findById(mettingU.getStudId());
        Metting metting=new Metting();
        metting.setTime(LocalTime.of(mettingU.gethM(),mettingU.getmM()));
        metting.setDate(mettingU.getDate());
        metting.setParent(parentOptional.get());
        metting.setProf(profOptional.get());
        metting.setSon(sonOptional.get());
        return mettingRepository.save(metting);




    }
}
