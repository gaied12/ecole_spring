package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.*;
import com.example.Ecoleback.Repository.*;
import com.example.Ecoleback.Service.IPushNotifService;
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
    SonRepository sonRepository;
    @Autowired
    ProfRepository profRepository;
    @Autowired
    IPushNotifService pushNotifService;



    @RequestMapping(value = "add/metting",method = RequestMethod.POST)
    public Metting addMett(@RequestBody MettingU mettingU){
        Optional<Prof>profOptional=profRepository.findById(mettingU.getProfId());
        Optional<Parent>parentOptional=parentRepository.findById(mettingU.getParentId());
        Optional<Son>sonOptional=sonRepository.findById(mettingU.getStudId());
        Metting metting=new Metting();
        metting.setState(MeetState.PENDING);
        metting.setTime(LocalTime.of(mettingU.gethM(),mettingU.getmM()));
        metting.setDate(mettingU.getDate());
        metting.setParent(parentOptional.get());
        metting.setProf(profOptional.get());
        metting.setSon(sonOptional.get());
        return mettingRepository.save(metting);




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
        pushNotifService.sendNotifToUser("votre rendez vous est confirmé",metting.getSon().getId());


        return metting ;



    }
    @RequestMapping(value = "mett/cancel/{id}",method = RequestMethod.GET)
    public Metting  cancel(@PathVariable Long id){

        Optional <Metting> mettingOptional=mettingRepository.findById(id);
        Metting metting=mettingOptional.get();
        metting.setState(MeetState.CANCEL);
        mettingRepository.save(metting);

        pushNotifService.sendNotifToUser("votre rendez vous est annulé",metting.getSon().getId());

        return metting ;



    }
    @RequestMapping(value = "all/metting/parent",method = RequestMethod.GET)
    public List<Metting>allbyParent(@RequestParam String parentId,@RequestParam String studId){


        List<Metting>list= mettingRepository.findAllByParentIdAndSonId(Long.valueOf(parentId),studId);
        int x =list.size();

        return list;




    }



}
