package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.*;
import com.example.Ecoleback.Repository.*;
import com.example.Ecoleback.Util.MettingU;
import com.example.Ecoleback.Util.SonU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class MettingController {
    @Autowired
    MettingRepository mettingRepository;
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    LevelRepository levelRepository ;
    @Autowired
    SonRepository sonRepository;
    @Autowired
    ProfRepository profRepository;



    @RequestMapping(value = "add/metting",method = RequestMethod.POST)
    public Metting addMett(@RequestBody MettingU mettingU){
        Optional<Prof>profOptional=profRepository.findById(mettingU.getProfId());
        Optional<Parent>parentOptional=parentRepository.findById(mettingU.getParentId());
        Optional<Son>sonOptional=sonRepository.findById(mettingU.getStudId());
        Optional<Level>levelOptional=levelRepository.findById(mettingU.getLevelId());
        Metting metting=new Metting();
        metting.setState(MeetState.PENDING);
        metting.setLevel(levelOptional.get());
        metting.setTime(LocalTime.of(mettingU.gethM(),mettingU.getmM()));
        metting.setDate(mettingU.getDate());
        metting.setParent(parentOptional.get());
        metting.setProf(profOptional.get());
        metting.setSon(sonOptional.get());
        return mettingRepository.save(metting);




    }
    @RequestMapping(value = "all/metting/profandlevel",method = RequestMethod.GET)
    public List<Metting>allbyProfandLevel(@RequestParam Long levelId,@RequestParam Long profId){


        return  mettingRepository.findAllByLevelIdAndProfId(levelId,profId);
    }
    @RequestMapping(value = "all/metting/prof/{id}",method = RequestMethod.GET)
    public List<Metting>allbyProf(@PathVariable Long id){


        List<Metting>list= mettingRepository.findAllByProfIdAndState(id,MeetState.PENDING);
        int x =list.size();
        return list;




    }
    @RequestMapping(value = "mett/confim/{id}",method = RequestMethod.GET)
    public Metting  confirm(@PathVariable Long id){
        Optional <Metting> mettingOptional=mettingRepository.findById(id);
        Metting metting=mettingOptional.get();
        metting.setState(MeetState.ACCEPTED);
        mettingRepository.save(metting);

        return metting ;



    }
    @RequestMapping(value = "mett/cancel/{id}",method = RequestMethod.GET)
    public Metting  cancel(@PathVariable Long id){

        Optional <Metting> mettingOptional=mettingRepository.findById(id);
        Metting metting=mettingOptional.get();
        metting.setState(MeetState.CANCEL);
        mettingRepository.save(metting);
        return metting ;



    }


}
